package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.entity.Address;
import com.kingdangkou.weixin.weixiaodan.entity.Customer;
import com.kingdangkou.weixin.weixiaodan.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

/**
 * Created by dongy on 2016-11-16.
 */
@Service
public class RegisterService {
    SessionFactory factory = new Configuration().configure().buildSessionFactory();
    public void save(Customer customer){
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
    }
    public void save(Address address){
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(address);
        transaction.commit();
    }

    public void save(Product product){
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(product);
        transaction.commit();
    }

    public Customer get(String openID){
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Customer> query = session.createQuery("select * from customers where openID = " + openID, Customer.class);
        Customer customer = query.getSingleResult();
        transaction.commit();
        return customer;
    }
}
