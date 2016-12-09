package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.CollectionDao;
import com.kingdangkou.weixin.weixiaodan.entity.CollectionEntity;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.model.Success;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dongy on 2016-12-07.
 */
@Service
public class CollectionService {
    @Autowired
    private CollectionDao collectionDao;
    public Result add(CollectionEntity entity) {
        collectionDao.save(entity);
        return new Success();
    }

    public List<CollectionEntity> get(String openID) {
        return collectionDao.find(openID);
    }

    public Result del(String id) {
        return del(id);
    }
}
