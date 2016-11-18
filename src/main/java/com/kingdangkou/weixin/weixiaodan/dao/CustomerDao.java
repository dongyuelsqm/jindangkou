package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.Customer;

/**
 * Created by dongy on 2016-11-19.
 */
public class CustomerDao extends BaseDaoHibernate4<Customer> {
    public Customer get(String openID){
        return get(Customer.class, openID);
    }
}
