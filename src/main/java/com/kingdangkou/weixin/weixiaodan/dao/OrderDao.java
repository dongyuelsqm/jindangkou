package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.Order;
import com.kingdangkou.weixin.weixiaodan.model.OrderModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dongy on 2016-11-18.
 */
@Component
public class OrderDao extends BaseDaoHibernate4<Order>{
    public List<OrderModel> findOrders(String openID){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List orders = session.createQuery("From Order where openID = " + openID).list();
        transaction.commit();
        return orders;
    }

    public Order getOrder(String key){
        return get(Order.class, key, "order_id");
    }


}
