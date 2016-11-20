package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.Customer;
import org.springframework.stereotype.Component;

/**
 * Created by dongy on 2016-11-19.
 */
@Component
public class CustomerDao extends BaseDaoHibernate4<Customer> {
    public Customer get(String openID){
        return get(Customer.class, openID);
    }
}
