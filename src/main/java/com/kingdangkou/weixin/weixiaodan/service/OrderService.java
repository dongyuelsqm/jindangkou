package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.AddressDao;
import com.kingdangkou.weixin.weixiaodan.dao.OrderDao;
import com.kingdangkou.weixin.weixiaodan.dao.ProductDao;
import com.kingdangkou.weixin.weixiaodan.entity.Address;
import com.kingdangkou.weixin.weixiaodan.entity.Order;
import com.kingdangkou.weixin.weixiaodan.entity.Product;
import com.kingdangkou.weixin.weixiaodan.model.OrderModel;
import com.kingdangkou.weixin.weixiaodan.model.Result;
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
    private ProductDao productDao;

    @Autowired
    private AddressDao addressDao;

    public OrderService() {}

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Result save(String customer_id, String product_id, int address_id){
        orderDao.save(new Order(customer_id, address_id));
        return new Result(true, "");
    }
    public OrderModel get(String id){
        Order order = orderDao.get(Order.class, id);
        Product product = productDao.get(Product.class, String.valueOf(order.getProduct_id()));
        Address address = addressDao.get(Address.class, String.valueOf(order.getAddress_id()));
        return new OrderModel(order, product, address);
    }

    public List<OrderModel> find(String openID){
        return orderDao.findOrders(openID);
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
