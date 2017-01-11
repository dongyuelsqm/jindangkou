package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.LabelEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dongy on 2017-01-06.
 */
@Component
public class LabelDao extends BaseDaoHibernate4<LabelEntity>{

    public List<LabelEntity> list() {
        return super.list(LabelEntity.class);
    }

    public LabelEntity get(String id){
        return get(LabelEntity.class, id);
    }
}
