package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.*;
import com.kingdangkou.weixin.weixiaodan.entity.*;
import com.kingdangkou.weixin.weixiaodan.model.ListResult;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.model.Success;
import com.kingdangkou.weixin.weixiaodan.utils.FileHandler;
import com.kingdangkou.weixin.weixiaodan.utils.JsonHandler;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by dongy on 2016-11-19.
 */
@Service
public class ProductService {
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private ProductDao productDao;

    @Autowired
    private ColorDao colorDao;

    @Autowired
    private SizeDao sizeDao;

    @Autowired
    private FileHandler fileHandler;

    @Autowired
    private LabelDao labelDao;

    public ProductEntity get(String id){
        return productDao.get(ProductEntity.class, id);
    }

    public ListResult list(){
        List<ProductEntity> productEntities = productDao.find();
        ListResult result = new ListResult(true, productEntities);
        return result;
    }

    public List<ProductEntity> list(String department){
        List<ProductEntity> productEntities = productDao.find("department", department, ProductEntity.class);
        return productEntities;
    }

    public Result save(ProductEntity productEntity){
        productDao.save(productEntity);
        moveFiles(productEntity.getPictures(), productEntity.getId());
        moveFiles(productEntity.getVideos(), productEntity.getId());
        return new Success();
    }

    public Result save(String name, String descriptive, float price, String department, String code, int minimum,
                       String postal,
                       String pictures,
                       String videos,
                       String quantity){
        DepartmentEntity departmentEntity = departmentDao.get(department);
        ProductEntity productEntity = new ProductEntity(name, descriptive, price, code, minimum, postal, pictures, videos);
        productEntity.setDepartment(departmentEntity);
        Set<ProductQuantityEntity> productQuantityEntitySet = convertJsonToProductEntitySet(quantity);
        productEntity.setProductQuantityEntitys(productQuantityEntitySet);
        productDao.save(productEntity);
        return new Success();
    }

    private Set<ProductQuantityEntity> convertJsonToProductEntitySet(String quantity) {
        Set<ProductQuantityEntity> productQuantityEntitySet = new HashSet<ProductQuantityEntity>();
        JSONArray jsonArray = JSONArray.fromObject(quantity);
        for (Object obj: jsonArray){
            JSONObject json = (JSONObject) obj;
            ProductQuantityEntity productQuantityEntity = new ProductQuantityEntity();
            productQuantityEntity.setNumber(Integer.valueOf(json.get("quantity").toString()));
            productQuantityEntity.setColorEntity(colorDao.get(json.get("color").toString()));
            productQuantityEntity.setSizeEntity(sizeDao.get(json.get("size").toString()));
            productQuantityEntitySet.add(productQuantityEntity);
        }
        return productQuantityEntitySet;
    }

    private void moveFiles(String fils, String id){
        fileHandler.moveFile(JsonHandler.toArrayList(fils), id);
    }

    public Result update(String id, String field, String value){
        productDao.update("product_id", id, field, value, ProductEntity.class);
        return new Success();
    }

    public Result updateNumber(String id, String color, String size, int number){
        ColorEntity colorEntity = colorDao.get(color);
        SizeEntity sizeEntity = sizeDao.get(size);
        productDao.updateQuantity(id, colorEntity, sizeEntity, number);
        return new Success();
    }

    public Result getNumber(String id, String color, String size){
        int quantity = productDao.getQuantity(id, color, size);
        return new Result(true, String.valueOf(quantity));
    }

    public Result remove(String id){
        ProductEntity productEntity = productDao.get(id);
        productDao.delete(productEntity);
        return new Success();
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<ProductEntity> getProductsByLabel(String label){
        List<ProductEntity> productEntities = productDao.find();
        LabelEntity entity = labelDao.get(label);
        removeProductsNotHaveLabel(productEntities, entity);
        return productEntities;
    }

    private void removeProductsNotHaveLabel(List<ProductEntity> productEntities, LabelEntity entity) {
        Iterator<ProductEntity> iterator = productEntities.iterator();
        while (iterator.hasNext()){
            ProductEntity next = iterator.next();
            if (!next.getLabelEntitySet().contains(entity)){
                productEntities.remove(next);
            }
        }
    }

    public static void main(String[] args) {
        List<String> strings = new ArrayList<String>();
        strings.add("a");
        strings.add("b");
        System.out.println(JSONArray.fromObject(strings));
    }
}
