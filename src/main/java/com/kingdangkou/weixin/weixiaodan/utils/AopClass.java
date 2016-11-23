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
    @Pointcut("execution(* com.kingdangkou.weixin.weixiaodan.utils.JustForAopLearn.testMethod(..))")
    private void testTestMethod(){}

    @Before("testTestMethod()")
    public void before() throws Exception {
        System.out.println("before point cut");
        throw new Exception("exception");
    }

    @After("testTestMethod()")
    public void after(){
        System.out.println("after point cut");
    }

}
