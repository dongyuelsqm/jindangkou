package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.TobePurchasedProductEntity;
import org.springframework.stereotype.Component;

/**
 * Created by dongy on 2016-12-07.
 */
@Component
public class TobePurchasedProductDao extends BaseDaoHibernate4<TobePurchasedProductEntity> {

    public TobePurchasedProductEntity get(String id){
        return get(TobePurchasedProductEntity.class, id);
    }
}
