package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.Address;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;

public class AddressDaoTest {

    @Test
    public void testFindAddress() throws Exception {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Address address = new Address("111", "zhejiang", "hangzhou", "", "");
        AddressDao dao = new AddressDao();
        dao.setSessionFactory(sessionFactory);
        dao.save(address);

        List<Address> addresses = dao.findAddresses("111");
        assertEquals("zhejiang", addresses.get(0).getProvince());
    }
}