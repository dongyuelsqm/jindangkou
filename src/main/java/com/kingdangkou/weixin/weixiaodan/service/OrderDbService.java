package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.model.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;

/**
 * Created by dongy on 2016-11-15.
 */
public class OrderDbService {
    SessionFactory factory = new Configuration().configure().buildSessionFactory();
    public void record(int customer_id, int product_id, int number, int address_id){
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(new Order(customer_id, product_id, number, address_id));
        transaction.commit();
    }

    public void get(int customer_id){
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        List orders = session.createQuery("From Order where customer_id = " + customer_id).list();

        Iterator iterator = orders.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    public static void main(String[] args) {
        OrderDbService service = new OrderDbService();
        service.get(1);
    }
}
