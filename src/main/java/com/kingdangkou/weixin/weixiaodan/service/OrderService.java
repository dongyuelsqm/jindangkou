package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.*;
import com.kingdangkou.weixin.weixiaodan.entity.*;
import com.kingdangkou.weixin.weixiaodan.enums.OrderStateEnum;
import com.kingdangkou.weixin.weixiaodan.model.Failure;
import com.kingdangkou.weixin.weixiaodan.model.ListResult;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.model.Success;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by dongy on 2016-11-15.
 */
@Service
public class OrderService {
    @Autowired
    private StorageDao storageDao;
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private ProductDao productDao;

    public OrderService() {}

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Result save(String openID, String items, String address_id){
        Order order = new Order();
        order.setOpenID(openID);
        order.setAddress(addressDao.get(address_id));
        order.setSubOrders(convertToSubOrders(items, order));

        orderDao.save(order);
        adjustProduct(order);
        return new Success();
    }

    private Set<SubOrder> convertToSubOrders(String items, Order order) {
        Set<SubOrder> subOrders = new HashSet<SubOrder>();
        JSONArray array = JSONArray.fromObject(items);
        for(Object obj: array){
            SubOrder entity = convertToEntity(order, (JSONObject) obj);
            subOrders.add(entity);
        }
        return subOrders;
    }

    private SubOrder convertToEntity(Order order, JSONObject obj) {
        SubOrder subOrder = new SubOrder(order);
        subOrder.setNumber(Integer.valueOf(obj.get("number").toString()));
        subOrder.setColor(obj.get("color").toString());
        subOrder.setSize(Integer.valueOf(obj.get("size").toString()));
        subOrder.setProductEntity(productDao.get(obj.get("product_id").toString()));
        return subOrder;
    }

    public void adjustProduct(Order obj){
        for (SubOrder subOrder: obj.getSubOrders()){
            String product_id = subOrder.getProductEntity().getId();
            String size = String.valueOf(subOrder.getSize());
            String color = subOrder.getColor();
            int sold = subOrder.getNumber();
            storageDao.update(product_id, color, size, sold);
        }
    }


    public Order get(String id){
        return orderDao.getOrder(id);
    }

    public ListResult find(String openID){
        List<Order> orders = orderDao.findAllOrders(openID);
        return new ListResult(true, orders);
    }

    public ListResult findState(String state){
        List<Order> orders = orderDao.listOrdersByState(state);
        return new ListResult(true, orders);
    }

    public List<Order> find(String openID, String state){
        return orderDao.find(openID, "state", state, Order.class);
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Result updateState(String id, String newState){
        if (OrderStateEnum.getEnum(Integer.valueOf(newState)) == null) return new Failure("badValue");
        updatePersistence(id, Integer.valueOf(newState));
        return new Success();
    }

    private void updatePersistence(String id, int newState) {
        Order order = orderDao.getOrder(id);
        order.setState(newState);
        orderDao.update(order);
    }
}
