package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dongy on 2016-11-18.
 */
@Component
public class OrderDao extends BaseDaoHibernate4<Order>{
    public List<Order> findAllMyOrders(String openID){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List orders = session.createQuery("from Order where customerEntity.openID = " + openID).list();
        transaction.commit();
        return orders;
    }

    public Order getOrder(String key){
        return get(Order.class, key, "id");
    }

    public void updateStateAndTransactionId(String id, int newState, String weixinTransactionId){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Order order = session.get(Order.class, Long.valueOf(id));
        order.setState(newState);
        order.setWeixinTransactionId(weixinTransactionId);
        session.saveOrUpdate(order);
        transaction.commit();
        session.close();
    }

    @Override
    public void save(Order entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
    }

    public List<Order> listOrdersByState(String state){
        return find("state", state, Order.class);
    }

    public List<Order> getOrdersByDate(String begin, String end) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List list = session.createQuery("from Order where date between :startDate and :endDate").
                setString("startDate", begin).setString("endDate", end).list();
        transaction.commit();
        return list;
    }

    public List<Order> find(String openID, String name, String value){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List list = session.createQuery("from Order where " + name + " = " + value + " and customerEntity.openID = " + openID).list();
        transaction.commit();
        return list;
    }
}
