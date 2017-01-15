package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.ColorDao;
import com.kingdangkou.weixin.weixiaodan.dao.ProductDao;
import com.kingdangkou.weixin.weixiaodan.dao.SizeDao;
import com.kingdangkou.weixin.weixiaodan.dao.TobePurchasedProductDao;
import com.kingdangkou.weixin.weixiaodan.entity.ColorEntity;
import com.kingdangkou.weixin.weixiaodan.entity.ProductEntity;
import com.kingdangkou.weixin.weixiaodan.entity.SizeEntity;
import com.kingdangkou.weixin.weixiaodan.entity.TobePurchasedProductEntity;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.model.Success;
import com.kingdangkou.weixin.weixiaodan.utils.configs.ProductStorageConfig;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dongy on 2016-12-06.
 */
@Service
public class ShopingCartService {
    @Autowired
    private ProductStorageConfig productStorageConfig;
    @Autowired
    private TobePurchasedProductDao tobePurchasedProductDao;

    @Autowired
    private ColorDao colorDao;
    @Autowired
    private SizeDao sizeDao;
    @Autowired
    private ProductDao productDao;

    public Result add(TobePurchasedProductEntity entity){
        tobePurchasedProductDao.save(entity);
        return new Result(true, "");
    }

    public Result add(String openId, String productId, String colorId, String sizeId, int number){
        ColorEntity colorEntity = colorDao.get(colorId);
        SizeEntity sizeEntity = sizeDao.get(sizeId);
        ProductEntity productEntity = productDao.get(productId);
        TobePurchasedProductEntity tobePurchasedProductEntity = new TobePurchasedProductEntity(openId, productEntity, colorEntity, sizeEntity, number);
        tobePurchasedProductDao.save(tobePurchasedProductEntity);
        return new Success();
    }

    public Result del(List<String> id_list) {
        for (String id : id_list){
            TobePurchasedProductEntity entity = tobePurchasedProductDao.get(id);
            tobePurchasedProductDao.delete(entity);
        }
        return new Result(true, "");
    }

    public Result list() {
        List<TobePurchasedProductEntity> list = tobePurchasedProductDao.list(TobePurchasedProductEntity.class);
        return new Result(true, JSONArray.fromObject(list, productStorageConfig));
    }
}
