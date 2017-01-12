package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.AddressDao;
import com.kingdangkou.weixin.weixiaodan.entity.Address;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.model.Success;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dongy on 2016-11-20.
 */
@Service
public class AddressService {
    @Autowired
    private AddressDao addressDao;

    public Result save(Address address){
        addressDao.save(address);
        return new Success();
    }

    public Result update(Address address){
        addressDao.update(address);
        return new Success();
    }

    public List<Address> list(String openID){
        return addressDao.findAddresses(openID);
    }

    public Result del(String id) {
        Address entity = addressDao.get(id);
        addressDao.delete(entity);
        return new Success();
    }
}
