package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.AddressDao;
import com.kingdangkou.weixin.weixiaodan.dao.OrderDao;
import com.kingdangkou.weixin.weixiaodan.dao.ProductDao;
import com.kingdangkou.weixin.weixiaodan.entity.Address;
import com.kingdangkou.weixin.weixiaodan.entity.Order;
import com.kingdangkou.weixin.weixiaodan.entity.SubOrder;
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

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private ProductDao productDao;

    public OrderService() {}

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Result save(String customer_id, String subOrders, String address_id){
        Address address = addressDao.get(address_id);
        Order order = new Order(customer_id);
        order.setAddress(address);
        orderDao.save(order);
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        JSONArray jsonArray = JSONArray.fromObject(subOrders);
        for(Object obj :jsonArray){
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            SubOrder subOrder = new SubOrder(order);
            JSONObject jsonObject = (JSONObject) obj;
            subOrder.setNumber(Integer.valueOf(jsonObject.get("number").toString()));
            subOrder.setColor(jsonObject.get("color").toString());
            subOrder.setSize(Integer.valueOf(jsonObject.get("size").toString()));
            subOrder.setProduct(productDao.get(jsonObject.get("product_id").toString()));
            session.persist(subOrder);
            transaction.commit();
        }
        return new Result(true, "");
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
}
