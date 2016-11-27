package com.kingdangkou.weixin.weixiaodan.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by dongy on 2016-11-26.
 */
@Aspect
@Component
public class AddressAop {
    @Pointcut("execution(* com.kingdangkou.weixin.weixiaodan.controller.AddressController(..))")
    public void register(){}

}
