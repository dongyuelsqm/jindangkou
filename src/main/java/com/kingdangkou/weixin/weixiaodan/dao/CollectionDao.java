package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.CollectionEntity;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Created by dongy on 2016-12-08.
 */
@Component
public class CollectionDao extends BaseDaoHibernate4<CollectionEntity>{

    public CollectionEntity get(String openID){
        return get(CollectionEntity.class, openID, "openID");
    }

    public List<CollectionEntity> find(String openID){
        return find("openID", openID, CollectionEntity.class);
    }
}
