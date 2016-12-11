package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.ProductDao;
import com.kingdangkou.weixin.weixiaodan.entity.Product;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.model.Success;
import com.kingdangkou.weixin.weixiaodan.utils.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongy on 2016-11-19.
 */
@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private FileHandler fileHandler;

    public Product get(String id){
        return productDao.get(Product.class, id);
    }

    public List<Product> list(){
        return productDao.find();
    }

    public Result save(Product product){
        productDao.save(product);
        fileHandler.moveFile(new ArrayList<String>(), String.valueOf(product.getId()));
        return new Success();
    }

    public void update(String id, String field, String value){
        productDao.update("product_id", id, field, value, Product.class);
    }

    public void remove(String id){
        Product product = productDao.get(id);
        productDao.delete(product);
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
}
