package com.kingdangkou.weixin.weixiaodan.utils;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by dongy on 2016-11-22.
 */
@Aspect
@Component
public class AopClass {
    @Pointcut("execution(* com.kingdangkou.weixin.weixiaodan.utils.JustForAopLearn.testMethod(..)) && args(param)")
    private void testTestMethod(String param){}

    @Before("testTestMethod(param)")
    public void before(String param) throws Exception {
        System.out.println("before point cut" + param);
    }

    @After("testTestMethod(param)")
    public void after(String param){
        System.out.println("after point cut " + param);
    }

}
