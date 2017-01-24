package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.Address;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dongy on 2016-11-18.
 */
@Component
public class AddressDao extends BaseDaoHibernate4<Address> {
    public AddressDao() {}
    public List<Address> findAddresses(String customerID){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List orders = session.createQuery("from Address where openID = :open_id").setString("open_id", customerID).list();
        transaction.commit();
        return orders;
    }
    public Address get(String addressID){
        return get(Address.class,addressID);
    }
}
