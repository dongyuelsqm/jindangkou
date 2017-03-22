package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.AddressDao;
import com.kingdangkou.weixin.weixiaodan.dao.StoreInfoDao;
import com.kingdangkou.weixin.weixiaodan.entity.Address;
import com.kingdangkou.weixin.weixiaodan.entity.StoreInfoEntity;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.model.Success;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dongy on 2017-03-20.
 */
@Service
public class StoreInfoService {
    @Autowired
    private StoreInfoDao storeInfoDao;

    @Autowired
    private AddressDao addressDao;
    public Result update(String username, String phone, String qq, String email, String name, String address, String tel, String picture) {
        StoreInfoEntity storeInfoEntity = storeInfoDao.get();
        updateAddress(storeInfoEntity.getAddress(), address, name, phone);
        addressDao.update(storeInfoEntity.getAddress());
        storeInfoEntity.setUsername(username);
        storeInfoEntity.setPhone(phone);
        storeInfoEntity.setQq(qq);
        storeInfoEntity.setEmail(email);
        storeInfoEntity.setName(name);
        storeInfoEntity.setTel(tel);
        storeInfoEntity.setPhone(picture);
        storeInfoDao.update(storeInfoEntity);
        return new Success();
    }

    public void updateAddress(Address address, String json, String name, String phone){
        JSONObject object = JSONObject.fromObject(json);
        String provinceName = object.getString("provinceName");
        String cityName = object.getString("cityName");
        String expAreaName = object.getString("expAreaName");
        String detail = object.getString("address");


        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setExpAreaName(expAreaName);
        address.setAddress(detail);
        address.setName(name);
        address.setMobile(phone);
    }
}
