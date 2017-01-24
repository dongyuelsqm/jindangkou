package com.kingdangkou.weixin.weixiaodan.aop;

import com.kingdangkou.weixin.weixiaodan.dao.AddressDao;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by dongy on 2016-11-26.
 */
@Aspect
@Component
public class AddressAop {
    @Autowired
    private AddressDao addressDao;
    @Around("execution(* com.kingdangkou.weixin.weixiaodan.controller.AddressController.delete(..))")
    public void deleteChecking(ProceedingJoinPoint jp){
        Object[] args = jp.getArgs();

    }

}
