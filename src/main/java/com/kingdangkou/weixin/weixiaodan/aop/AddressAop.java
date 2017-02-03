package com.kingdangkou.weixin.weixiaodan.aop;

import com.kingdangkou.weixin.weixiaodan.dao.AddressDao;
import com.kingdangkou.weixin.weixiaodan.entity.Address;
import com.kingdangkou.weixin.weixiaodan.exception.ParaInValidException;
import com.kingdangkou.weixin.weixiaodan.model.Failure;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
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
    @Around("execution(* com.kingdangkou.weixin.weixiaodan.service.AddressService.del(..))")
    public Object deleteChecking(ProceedingJoinPoint jp) throws Throwable {
        Object[] args = jp.getArgs();
        Address address = addressDao.get(args[0].toString());
        if (address == null)
            return new Failure("invalid address id");
        Object proceed = jp.proceed(args);
        return proceed;
    }

}
