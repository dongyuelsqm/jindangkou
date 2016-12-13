package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.ProductDao;
import com.kingdangkou.weixin.weixiaodan.entity.Product;
import com.kingdangkou.weixin.weixiaodan.entity.ProductQuantityEntity;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.model.Success;
import com.kingdangkou.weixin.weixiaodan.utils.FileHandler;
import com.kingdangkou.weixin.weixiaodan.utils.JsonHandler;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        moveFiles(product.getPictures(), product.getId());
        moveFiles(product.getVideos(), product.getId());
        return new Success();
    }

    public Result save(String name, float price, String department, String code, int minimum,
                       String postal,
                       String pictures,
                       String videos,
                       String quantity){
        Product product = new Product(name,price, department, code, minimum, postal, pictures, videos);
        Set<ProductQuantityEntity> productQuantityEntitySet = convertJsonToProductEntitySet(quantity);
        product.setProductQuantityEntitys(productQuantityEntitySet);
        productDao.save(product);
        return new Success();
    }

    private Set<ProductQuantityEntity> convertJsonToProductEntitySet(String quantity) {
        Set<ProductQuantityEntity> productQuantityEntitySet = new HashSet<ProductQuantityEntity>();
        JSONArray jsonArray = JSONArray.fromObject(quantity);
        for (Object obj: jsonArray){
            JSONObject json = (JSONObject) obj;
            ProductQuantityEntity productQuantityEntity = new ProductQuantityEntity();
            productQuantityEntity.setNumber(Integer.valueOf(json.get("quantity").toString()));
            productQuantityEntity.setColor(Integer.valueOf(json.get("color").toString()));
            productQuantityEntity.setSize(Integer.valueOf(json.get("size").toString()));
            productQuantityEntitySet.add(productQuantityEntity);
        }
        return productQuantityEntitySet;
    }

    private void moveFiles(String fils, int id){
        fileHandler.moveFile(JsonHandler.toArrayList(fils), String.valueOf(id));
    }

    public Result update(String id, String field, String value){
        productDao.update("product_id", id, field, value, Product.class);
        return new Success();
    }

    public Result updateNumber(String id, int number){
        Product product = productDao.get(id);
//        product.setNumber(number);
//        productDao.update(product);
        return new Success();
    }

    public int getNumber(String id){
        Product product = productDao.get(id);
//        return product.getNumber();
        return 0;
    }

    public Result remove(String id){
        Product product = productDao.get(id);
        productDao.delete(product);
        return new Success();
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
}
