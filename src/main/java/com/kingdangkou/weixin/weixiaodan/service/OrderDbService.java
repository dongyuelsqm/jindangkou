package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.OrderDao;
import com.kingdangkou.weixin.weixiaodan.entity.Order;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dongy on 2016-11-15.
 */
@Service
public class OrderDbService {
    @Autowired
    private OrderDao orderDao;

    public OrderDbService() {}

    public OrderDbService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Result save(String customer_id, int product_id, int number, int address_id){
        orderDao.save(new Order(customer_id, product_id, number, address_id));
        return new Result(true, "");
    }
    public Order get(String id){
        return orderDao.get(Order.class, id);
    }

    public List<Order> find(String openID){
        return orderDao.findOrders(openID);
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
