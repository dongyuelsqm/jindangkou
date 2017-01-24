package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.SizeEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

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

    public HashMap<Integer, SizeEntity> getMap(){
        List<SizeEntity> list = list(SizeEntity.class);
        HashMap<Integer, SizeEntity> map = new HashMap<>();
        for (SizeEntity entity: list){
            map.put(entity.getId(), entity);
        }
        return map;
    }
}
