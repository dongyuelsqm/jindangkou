package com.kingdangkou.weixin.weixiaodan.service.utils;

import com.kingdangkou.weixin.weixiaodan.dao.ColorDao;
import com.kingdangkou.weixin.weixiaodan.dao.SizeDao;
import com.kingdangkou.weixin.weixiaodan.entity.StorageEntity;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dongy on 2017-01-15.
 */
@Component
public class ProductStorageGenerator {
    @Autowired
    private ColorDao colorDao;
    @Autowired
    private SizeDao sizeDao;

    public Set<StorageEntity> parse2StorageSet(String quantity) {
        Set<StorageEntity> productQuantityEntitySet = new HashSet<>();
        JSONArray jsonArray = JSONArray.fromObject(quantity);
        for (Object obj: jsonArray){
            JSONObject json = (JSONObject) obj;
            StorageEntity productQuantityEntity = new StorageEntity();
            productQuantityEntity.setNumber(Integer.valueOf(json.get("quantity").toString()));
            productQuantityEntity.setColorEntity(colorDao.get(json.get("color").toString()));
            productQuantityEntity.setSizeEntity(sizeDao.get(json.get("size").toString()));
            productQuantityEntitySet.add(productQuantityEntity);
        }
        return productQuantityEntitySet;
    }
}
