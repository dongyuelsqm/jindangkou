package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.Address;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dongy on 2016-11-18.
 */
@Component
public class AddressDao extends BaseDaoHibernate4<Address> {
    public List<Address> findAddresses(String customerID){
        return find("From Address where openID = " + customerID);
    }
}
