package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.Order;
import org.aspectj.weaver.ast.Or;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by dongy on 2017-03-22.
 */
@Repository
public class OrderStatisticsDao extends BaseDaoHibernate4<Order>{
    public HashMap<String, List<Order>> getOrders(Object[] dates){
        HashMap<String, List<Order>> orders = new HashMap<>();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        for (Object date: dates){
            List<Order> list = session.createQuery("from Order where date like '" + date + "%'").list();
            orders.put(date.toString(), list);

        }
        transaction.commit();
        session.close();
        return orders;
    }

    public List getOrdersByDistricts(Object[] districts){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List list = session.createQuery("select count (o.id) as order_number, sum(o.actual_price) as money, o.address from Order as o group by o.address").list();
        transaction.commit();
        session.close();
        return list;
    }
}
