package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

/**
 * Created by dongy on 2016-11-30.
 */
@Component
public class UserDao extends BaseDaoHibernate4<User> {
    public User get(String name, String password){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "From User where userName=:username and password=:password";
        User obj = session.createQuery(hql, User.class).setString("username", name).setString("password", password).uniqueResult();
        transaction.commit();
        session.close();
        return obj;
    }
}
