package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.*;
import com.kingdangkou.weixin.weixiaodan.entity.*;
import com.kingdangkou.weixin.weixiaodan.enums.OrderStateEnum;
import com.kingdangkou.weixin.weixiaodan.model.Failure;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.model.Success;
import com.kingdangkou.weixin.weixiaodan.utils.configs.OrderJsonConfig;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by dongy on 2016-11-15.
 */
@Service
public class OrderService {
    @Autowired
    private OrderJsonConfig orderJsonConfig;
    @Autowired
    private StorageDao storageDao;
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
    @Autowired
    private SubOrderEntityDao subOrderEntityDao;

    @Autowired
    private CustomerDao customerDao;
    public OrderService() {}

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Result save(String openID, String items, String address_id){
        Order order = new Order();
        CustomerEntity customer = customerDao.get(CustomerEntity.class, openID, "openID");
        order.setCustomerEntity(customer);
        order.setAddress(addressDao.get(address_id));
        order.setSubOrders(convertToSubOrders(items, order));
        orderDao.save(order);
        float total = calculateMethodPrice(order);
        order.setMethod_price(total);
        order.setActual_price(total);
        adjustStorage(order.getSubOrders());
        return new Success();
    }

    private float calculateMethodPrice(Order order) {
        float total = 0;
        for (SubOrder subOrder: order.getSubOrders()){
            total += subOrder.getNumber() * subOrder.getProductEntity().getPrice();
        }
        return total;
    }

    private Set<SubOrder> convertToSubOrders(String items, Order order) {
        Set<SubOrder> subOrders = new HashSet<SubOrder>();
        JSONArray array = JSONArray.fromObject(items);
        HashMap<Integer, ColorEntity> colors = colorDao.getColorMaps();
        HashMap<Integer, SizeEntity> sizes = sizeDao.getMap();
        for(Object obj: array){
            ProductEntity productEntity = productDao.get(((JSONObject) obj).get("product_id").toString());
            int color_id = Integer.valueOf(((JSONObject) obj).get("color").toString());
            int size_id = Integer.valueOf(((JSONObject) obj).get("size").toString());
            Integer number = Integer.valueOf(((JSONObject) obj).get("number").toString());

            SubOrder subOrder = new SubOrder(order, productEntity, colors.get(color_id), sizes.get(size_id), number);
            //subOrderEntityDao.save(subOrder);
            subOrders.add(subOrder);
        }
        return subOrders;
    }

    public void adjustStorage(Set<SubOrder> subOrders){
        for (SubOrder subOrder: subOrders){
            int sold = subOrder.getNumber();
            ProductEntity productEntity = subOrder.getProductEntity();
            ColorEntity color = subOrder.getColor();
            SizeEntity size = subOrder.getSize();
            storageDao.update(productEntity.getId(), color.getId(), size.getId(), sold);
        }
    }


    public Order get(String id){
        return orderDao.getOrder(id);
    }

    public Result find(String openID){
        List<Order> orders = orderDao.findAllMyOrders(openID);
        return new Result(true, JSONArray.fromObject(orders, orderJsonConfig));
    }

    public Result findState(String state){
        List<Order> orders = orderDao.find("state", state, Order.class);
        return new Result(true, JSONArray.fromObject(orders, orderJsonConfig));
    }

    public Result find(String openID, String state){
        List<Order> orders = orderDao.find(openID, "state", state);
        return new Result(true, JSONArray.fromObject(orders, orderJsonConfig));
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Result updateState(int id, String newState){
        if (OrderStateEnum.getEnum(Integer.valueOf(newState)) == null) return new Failure("badValue");
        updatePersistence(id, Integer.valueOf(newState));
        return new Success();
    }

    private Result updatePersistence(int id, int newState) {
        orderDao.updateState(id, newState);
        return new Success();
    }

    public Result getOrderByDate(String begin, String end) {
        List<Order> ordersByDate = orderDao.getOrdersByDate(begin, end);
        return new Result(true, JSONArray.fromObject(ordersByDate, orderJsonConfig));
    }
}
