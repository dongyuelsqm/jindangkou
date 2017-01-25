package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.SizeDao;
import com.kingdangkou.weixin.weixiaodan.entity.SizeEntity;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.model.Success;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dongy on 2016-12-17.
 */
@Service
public class SizeService {
    @Autowired
    private SizeDao sizeDao;

    public Result add(String name){
        SizeEntity entity = new SizeEntity();
        entity.setName(name);
        sizeDao.save(entity);
        return new Success();
    }

    public Result del(String id){
        SizeEntity entity = sizeDao.get(SizeEntity.class, id);
        sizeDao.delete(entity);
        return new Success();
    }

    public Result list() {
        List<SizeEntity> list = sizeDao.list(SizeEntity.class);
        return new Result(true, JSONArray.fromObject(list));
    }
}
