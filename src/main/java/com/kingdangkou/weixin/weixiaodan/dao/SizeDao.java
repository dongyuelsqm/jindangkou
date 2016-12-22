package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.SizeEntity;
import org.springframework.stereotype.Component;

/**
 * Created by dongy on 2016-12-17.
 */
@Component
public class SizeDao extends BaseDaoHibernate4<SizeEntity>{
    public void del(String id){
        SizeEntity sizeEntity = get(SizeEntity.class, id);
        delete(sizeEntity);
    }

    public SizeEntity get(String id){
        return get(SizeEntity.class, id);
    }
}
