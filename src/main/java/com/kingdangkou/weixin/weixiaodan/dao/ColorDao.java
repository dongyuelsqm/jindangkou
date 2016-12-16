package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.Color;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dongy on 2016-12-16.
 */
@Component
public class ColorDao extends BaseDaoHibernate4<Color>{
    public void add(String color) {
        save(new Color(color));
    }

    public List<Color> list() {
        return list(Color.class);
    }
}
