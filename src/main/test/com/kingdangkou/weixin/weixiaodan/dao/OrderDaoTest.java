package com.kingdangkou.weixin.weixiaodan.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class OrderDaoTest {

    @Test
    public void testFind() throws Exception {
        OrderDao orderDao = new OrderDao();
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        orderDao.setSessionFactory(sessionFactory);
        assertTrue(orderDao.findAllOrders("1").size()>0 );
    }

    @Test
    public void testGet() throws Exception {
        OrderDao orderDao = new OrderDao();
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        orderDao.setSessionFactory(sessionFactory);
        assertEquals("1", orderDao.getOrder("2").getOpenID());
    }
}