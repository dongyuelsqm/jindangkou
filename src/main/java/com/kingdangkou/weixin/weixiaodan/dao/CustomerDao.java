package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.CustomerEntity;
import org.springframework.stereotype.Component;

/**
 * Created by dongy on 2017-01-12.
 */
@Component
public class CustomerDao extends BaseDaoHibernate4<CustomerEntity>{
    public CustomerEntity get(String openID){
        return get(CustomerEntity.class, openID, "openID");
    }
}
