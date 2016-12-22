package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.*;
import com.kingdangkou.weixin.weixiaodan.entity.*;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.model.Success;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dongy on 2016-11-15.
 */
@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ColorDao colorDao;

    @Autowired
    private SizeDao sizeDao;

    public OrderService() {}

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Result save(String customer_id, String subOrders, String address_id){
        Address address = addressDao.get(address_id);
        Order order = new Order(customer_id);
        order.setAddress(address);
        JSONArray items = JSONArray.fromObject(subOrders);
        for(Object obj: items){
            SubOrder entity = convertToEntity(order, (JSONObject) obj);
            order.getSubOrders().add(entity);
        }
        orderDao.save(order);
        adjustInventory(order);
        return new Result(true, "");
    }

    private SubOrder convertToEntity(Order order, JSONObject obj) {
        SubOrder subOrder = new SubOrder(order);
        subOrder.setNumber(Integer.valueOf(obj.get("number").toString()));
        subOrder.setColor(obj.get("color").toString());
        subOrder.setSize(Integer.valueOf(obj.get("size").toString()));
        subOrder.setProduct(productDao.get(obj.get("product_id").toString()));
        return subOrder;
    }

    public void adjustInventory(Order obj){
        for (SubOrder subOrder: obj.getSubOrders()){
            String product_id = subOrder.getProduct().getId();
            String size = String.valueOf(subOrder.getSize());
            String color = subOrder.getColor();
            SizeEntity sizeEntity = sizeDao.get(size);
            ColorEntity colorEntity = colorDao.get(color);
            int sold = subOrder.getNumber();
            int current = productDao.getQuantity(product_id, color, size);
            current = current - sold;
            productDao.updateQuantity(product_id, colorEntity, sizeEntity, current);
        }
    }


    public Order get(String id){
        return orderDao.getOrder(id);
    }

    public List<Order> find(String openID){
        return orderDao.findAllOrders(openID);
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

    public Result updateState(String id, int newState){
        updatePersistence(id, newState);
        return new Success();
    }

    private void updatePersistence(String id, int newState) {
        Order order = orderDao.getOrder(id);
        order.setState(newState);
        orderDao.update(order);
    }
}
