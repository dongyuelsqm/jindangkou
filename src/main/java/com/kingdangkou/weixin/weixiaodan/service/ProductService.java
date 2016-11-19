package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.ProductDao;
import com.kingdangkou.weixin.weixiaodan.entity.Product;
import org.springframework.stereotype.Service;

/**
 * Created by dongy on 2016-11-19.
 */
@Service
public class ProductService {
    private ProductDao productDao;

    public Product get(String id){
        return productDao.get(Product.class, id);
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
}
