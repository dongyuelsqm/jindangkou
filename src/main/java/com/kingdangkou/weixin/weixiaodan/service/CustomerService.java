package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.CustomerDao;
import com.kingdangkou.weixin.weixiaodan.entity.CustomerEntity;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.token.AccessTokenHolder;
import com.kingdangkou.weixin.weixiaodan.token.CustomerInfoGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dongy on 2017-01-12.
 */
@Service
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private CustomerInfoGetter customerInfoGetter;
    @Autowired
    private AccessTokenHolder accessTokenHolder;

    public void save(Result result) throws Exception {
        String openid = (String) result.getDetail();
        CustomerEntity customerEntity = customerDao.get(CustomerEntity.class, openid);
        if (customerEntity != null){
            CustomerEntity entity = customerInfoGetter.sendGet(accessTokenHolder.getAccess_Token(), openid);
            customerDao.save(entity);
        }
    }

    public Result get(String openid) throws Exception {
        CustomerEntity customerEntity = customerDao.get(CustomerEntity.class, openid);
        if (customerEntity != null){
            CustomerEntity entity = customerInfoGetter.sendGet(accessTokenHolder.getAccess_Token(), openid);
            customerDao.save(entity);
            return new Result(true, entity);
        }
        return new Result(true, customerEntity);
    }
}
