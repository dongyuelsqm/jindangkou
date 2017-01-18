package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.CollectionDao;
import com.kingdangkou.weixin.weixiaodan.dao.ProductDao;
import com.kingdangkou.weixin.weixiaodan.entity.CollectionEntity;
import com.kingdangkou.weixin.weixiaodan.entity.ProductEntity;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.model.Success;
import com.kingdangkou.weixin.weixiaodan.utils.configs.ProductStorageConfig;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dongy on 2016-12-07.
 */
@Service
public class CollectionService {
    @Autowired
    private ProductStorageConfig config;
    @Autowired
    private CollectionDao collectionDao;

    @Autowired
    private ProductDao productDao;
    public Result add(String product_id, String openID) {
        ProductEntity productEntity = productDao.get(product_id);
        CollectionEntity entity = new CollectionEntity();
        entity.setProductEntity(productEntity);
        entity.setOpenID(openID);
        collectionDao.save(entity);
        return new Success();
    }

    public Result get(String openID) {
        List<CollectionEntity> collectionEntities = collectionDao.find(openID);
        return new Result(true, JSONArray.fromObject(collectionEntities, config));
    }

    public Result del(String id) {
        return del(id);
    }
}
