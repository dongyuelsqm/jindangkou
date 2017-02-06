package com.kingdangkou.weixin.weixiaodan.aop;

import com.kingdangkou.weixin.weixiaodan.dao.AddressDao;
import com.kingdangkou.weixin.weixiaodan.dao.ColorDao;
import com.kingdangkou.weixin.weixiaodan.dao.ProductDao;
import com.kingdangkou.weixin.weixiaodan.dao.SizeDao;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by apple on 17/2/3.
 */
@Aspect
@Component
public class OrderAop {
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private ColorDao colorDao;
    @Autowired
    private SizeDao sizeDao;
    @Autowired
    private ProductDao productDao;


}
