package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.StoreInfoEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by dongy on 2017-03-20.
 */
@Repository
public class StoreInfoDao extends BaseDaoHibernate4<StoreInfoEntity>{
    @Override
    public void update(StoreInfoEntity entity) {
        super.update(entity);
    }

    public StoreInfoEntity get(){
        return get(StoreInfoEntity.class, "1");
    }
}
