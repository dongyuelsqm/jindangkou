package com.kingdangkou.weixin.weixiaodan.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


/**
 * Created by dongy on 2016-11-26.
 */
@Component
@Aspect
public class ControllerExceptionAop {
    Logger logger = Logger.getLogger(this.getClass());
    @Pointcut("execution(* com.kingdangkou.weixin.weixiaodan.controller.*(..))")
    public void exceptionRecorder(){}

    @AfterThrowing(value = "exceptionRecorder()", throwing = "ex")
    public void recordException(Throwable ex){
        logger.error(ex.getMessage(), ex);
    }

}
