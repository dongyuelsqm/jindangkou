package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.ColorDao;
import com.kingdangkou.weixin.weixiaodan.entity.Color;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.model.Success;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dongy on 2016-12-16.
 */
@Service
public class ColorService {
    @Autowired
    private ColorDao colorDao;

    public Result add(String color) {
        colorDao.add(color);
        return new Success();
    }

    public List<Color> list() {
        return colorDao.list();
    }

    public Result del(String id) {
        Color color = colorDao.get(Color.class, id);
        colorDao.delete(color);
        return new Success();
    }

    public ColorDao getColorDao() {
        return colorDao;
    }

    public void setColorDao(ColorDao colorDao) {
        this.colorDao = colorDao;
    }
}
