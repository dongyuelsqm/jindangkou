package com.kingdangkou.weixin.weixiaodan.controller;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("")
public class TakeNewOrderTest {
    private BasicDataSource dataSource;
    @Test
    public void testName() throws Exception {
        BasicDataSource ds=new BasicDataSource();
        ds.setUrl("jdbc:mysql://127.0.0.1:3306/emp");
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUsername("root");
        ds.setPassword("root123456");
        Connection con=ds.getConnection();
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select * from product_order");
        while (rs.next()){
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString(2));
        }
        stmt.close();
        con.close();
    }

    @Test
    public void testQueryOrderInfo() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("spitter-servlet.xml");
        dataSource = (BasicDataSource) context.getBean("dataSource");
        //takeNewOrder.doGet();
    }
}