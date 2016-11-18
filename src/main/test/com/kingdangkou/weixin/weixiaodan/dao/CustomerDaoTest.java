package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.Customer;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class CustomerDaoTest {

    @Test
    public void testGet() throws Exception {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Customer customer = new Customer("111", "zhangsan", "xx", "122121");
        CustomerDao dao = new CustomerDao();
        dao.setSessionFactory(sessionFactory);
        dao.save(customer);

        assertEquals("zhangsan", dao.get("111").getName());
    }
}