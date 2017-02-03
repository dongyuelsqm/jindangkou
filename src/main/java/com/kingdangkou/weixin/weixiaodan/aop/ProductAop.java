package com.kingdangkou.weixin.weixiaodan.aop;

import com.kingdangkou.weixin.weixiaodan.dao.DepartmentDao;
import com.kingdangkou.weixin.weixiaodan.entity.DepartmentEntity;
import com.kingdangkou.weixin.weixiaodan.exception.ParaInValidException;
import com.kingdangkou.weixin.weixiaodan.model.Failure;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by apple on 17/1/31.
 */
@Aspect
@Component
public class ProductAop {
    @Autowired
    private DepartmentDao departmentDao;

    @Around("execution(* com.kingdangkou.weixin.weixiaodan.service.ProductService.save(..))")
    public Object checkDepartment(ProceedingJoinPoint jp) throws Throwable {
        Object[] args = jp.getArgs();
        DepartmentEntity entity = departmentDao.get(args[3].toString());
        if (entity == null)
            return new Failure("invalid department id");
        Object returns = jp.proceed(args);
        return returns;
    }
}
