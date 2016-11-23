package com.kingdangkou.weixin.weixiaodan.utils;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JustForAopLearnTest {

    @Test
    public void testTestMethod() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("file:src/main/webapp/WEB-INF/spitter-servlet.xml");
        JustForAopLearn test = (JustForAopLearn) ctx.getBean("test");
        try {
            test.testMethod();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    @Test
    public void testControlMethod() throws Exception {

    }
}