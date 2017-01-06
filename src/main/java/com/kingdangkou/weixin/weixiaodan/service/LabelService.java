package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.LabelDao;
import com.kingdangkou.weixin.weixiaodan.entity.LabelEntity;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.model.Success;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by dongy on 2017-01-06.
 */
@Service
public class LabelService {
    private HashMap<String, LabelEntity> entities = new HashMap<>();
    @Autowired
    private LabelDao labelDao;
    public Result get() {
        List<LabelEntity> list = labelDao.list();
        return new Result(true, JSONArray.fromObject(list).toString());
    }

    public Result add(String label) {
        labelDao.save(new LabelEntity(label));
        return new Success();
    }

    public Result delete(String id) {
        LabelEntity entity = entities.get(id);
        if (entity == null)
            entity = labelDao.get(id);
        labelDao.delete(entity);
        return new Success();
    }
}
