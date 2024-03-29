package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.ColorEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by dongy on 2016-12-16.
 */
@Component
public class ColorDao extends BaseDaoHibernate4<ColorEntity>{
    public void add(String color) {
        save(new ColorEntity(color));
    }

    public ColorEntity get(String id){
        return get(ColorEntity.class, id);
    }

    public List<ColorEntity> list() {
        return list(ColorEntity.class);
    }

    public HashMap<Integer, ColorEntity> getColorMaps(){
        List<ColorEntity> list = list(ColorEntity.class);
        HashMap<Integer, ColorEntity> map = new HashMap<>();
        for (ColorEntity entity: list){
            map.put(entity.getId(), entity);
        }
        return map;
    }
}
