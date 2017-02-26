package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.ColorDao;
import com.kingdangkou.weixin.weixiaodan.entity.ColorEntity;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.model.Success;
import net.sf.json.JSONArray;
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

    public Result list() {
        List<ColorEntity> list = colorDao.list();
        return new Result(true, JSONArray.fromObject(list));
    }

    public Result del(String id) {
        ColorEntity colorEntity = colorDao.get(ColorEntity.class, id);
        colorDao.delete(colorEntity);
        return new Success();
    }

    public ColorDao getColorDao() {
        return colorDao;
    }

    public void setColorDao(ColorDao colorDao) {
        this.colorDao = colorDao;
    }
}
