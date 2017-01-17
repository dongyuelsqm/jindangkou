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
    public List<Order> findAllOrders(String openID){
        return find("openID", openID, Order.class);
    }

    public Order getOrder(String key){
        return get(Order.class, key, "id");
    }

    @Override
    public void save(Order entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
    }

    public void updateState(int id, int newSate){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Order order = session.get(Order.class, id);
        order.setState(newSate);
        session.update(order);
        transaction.commit();
    }

    public List<Order> listOrdersByState(String state){
        return find("state", state, Order.class);
    }
}
