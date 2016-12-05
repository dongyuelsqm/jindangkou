package com.kingdangkou.weixin.weixiaodan.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spitter-servlet.xml")
public class AddressTest {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("file:src/main/webapp/WEB-INF/spitter-servlet.xml");
    Address address =(Address) ctx.getBean("address");
    @Test
    public void testSetProvince() throws Exception {
        address.setProvince("///");
    }
}