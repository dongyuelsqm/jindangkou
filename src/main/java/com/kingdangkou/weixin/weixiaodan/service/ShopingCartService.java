package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.TobePurchasedProductDao;
import com.kingdangkou.weixin.weixiaodan.entity.TobePurchasedProductEntity;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dongy on 2016-12-06.
 */
@Service
public class ShopingCartService {
    @Autowired
    private TobePurchasedProductDao tobePurchasedProductDao;
    public Result add(TobePurchasedProductEntity entity){
        tobePurchasedProductDao.save(entity);
        return new Result(true, "");
    }

    public Result del(List<String> id_list) {
        for (String id : id_list){
            TobePurchasedProductEntity entity = tobePurchasedProductDao.get(id);
            tobePurchasedProductDao.delete(entity);
        }
        return new Result(true, "");
    }
}