package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.AddressDao;
import com.kingdangkou.weixin.weixiaodan.dao.CustomerDao;
import com.kingdangkou.weixin.weixiaodan.dao.OrderDao;
import com.kingdangkou.weixin.weixiaodan.dao.StorageDao;
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
    private SubOrderService subOrderService;

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private UnifiedOrderService unifiedOrderService;

    public OrderService() {}

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Result save(String openID, String items, String address_id) throws Exception {
        CustomerEntity customerEntity = customerDao.get(openID);
        Address address = addressDao.get(address_id);


        Order order = new Order();
        order.setCustomerEntity(customerEntity);
        order.setAddress(address);

        Result ex = putSubOrdersToOrder(items, order);
        if (ex != null) return ex;

        adjustStorage(order.getSubOrders());

        orderDao.save(order);
        String jsAPIConfig = unifiedOrderService.unifiedOrder(openID, String.valueOf(order.getId()), (int) (order.getActual_price() * 100), items);
        return new Result(true, jsAPIConfig);
    }

    private Result putSubOrdersToOrder(String items, Order order) {
        try {
            Set<SubOrder> subOrderSet = subOrderService.convertToSubOrders(items);
            order.setSubOrders(subOrderSet);
        }catch (Exception ex){
            return new Failure(ex.getMessage());
        }

        float total = calculateMethodPrice(order);
        order.setMethod_price(total);
        order.setActual_price(total);
        return null;
    }

    private float calculateMethodPrice(Order order) {
        float total = 0;
        for (SubOrder subOrder: order.getSubOrders()){
            total += subOrder.getNumber() * subOrder.getProductEntity().getPrice();
        }
        return total;
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

    public Result updateStateAndTransactionId(String id, String newState, String weixinTransactionId){
        Order order = orderDao.getOrder(id);
        if (OrderStateEnum.getEnum(Integer.valueOf(newState)) == null) return new Failure("badValue");
        order.setWeixinTransactionId(weixinTransactionId);
        order.setState(Integer.valueOf(newState));
        orderDao.update(order);
        return new Success();
    }

    public Result updateState(String id, String newState){
        Order order = orderDao.getOrder(id);
        if (OrderStateEnum.getEnum(Integer.valueOf(newState)) == null) return new Failure("badValue");
        order.setState(Integer.valueOf(newState));
        orderDao.update(order);
        return new Success();
    }

    public Result getOrderByDate(String begin, String end) {
        List<Order> ordersByDate = orderDao.getOrdersByDate(begin, end);
        return new Result(true, JSONArray.fromObject(ordersByDate, orderJsonConfig));
    }

    public Result list(){
        List<Order> list = orderDao.list(Order.class);
        return new Result(true, JSONArray.fromObject(list, orderJsonConfig));
    }

    public Result addOrder(String name, String sub_orders, String address) {
        Address addressEntity = (Address) JSONObject.toBean(JSONObject.fromObject(address), Address.class);
        addressDao.save(addressEntity);
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setNickname(name);
        customerEntity.setOpenID("default");
        customerDao.save(customerEntity);
        Order order = new Order();
        putSubOrdersToOrder(sub_orders, order);
        order.setCustomerEntity(customerEntity);
        order.setAddress(addressEntity);
        adjustStorage(order.getSubOrders());
        orderDao.save(order);
        return new Success();
    }
}
