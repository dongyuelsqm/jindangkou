package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.model.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Created by dongy on 2016-11-15.
 */
public class OrderDbService {
    public void record(int customer_id, int product_id, int number, int address_id){
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(new Order(customer_id, product_id, number, address_id));
        transaction.commit();
    }

    public static void main(String[] args) {
        OrderDbService service = new OrderDbService();
        service.record(1,1,1,1);
    }
}
