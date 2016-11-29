package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.OrderDao;
import com.kingdangkou.weixin.weixiaodan.entity.Order;
import com.kingdangkou.weixin.weixiaodan.entity.SubOrder;
import com.kingdangkou.weixin.weixiaodan.model.OrderModel;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
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

    public OrderService() {}

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Result save(String customer_id, String subOrders, String address_id){
        Order order = new Order(customer_id, address_id);
        orderDao.save(order);
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        JSONArray jsonArray = JSONArray.fromObject(subOrders);
        for(Object obj :jsonArray){
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            SubOrder subOrder = (SubOrder) JSONObject.toBean((JSONObject) obj, SubOrder.class);
            subOrder.setOrder(order);
            session.persist(subOrder);
            transaction.commit();
        }
        return new Result(true, "");
    }
    public OrderModel get(String id){
//        Order order = orderDao.get(Order.class, id);
//        Product product = productDao.get(Product.class, String.valueOf(order.getProduct_id()));
//        Address address = addressDao.get(Address.class, String.valueOf(order.getAddress_id()));
//        return new OrderModel(order, product, address);
        return null;
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
