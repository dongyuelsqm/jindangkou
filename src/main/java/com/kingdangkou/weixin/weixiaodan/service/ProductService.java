package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.*;
import com.kingdangkou.weixin.weixiaodan.entity.*;
import com.kingdangkou.weixin.weixiaodan.model.Failure;
import com.kingdangkou.weixin.weixiaodan.model.ProductModel;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.model.Success;
import com.kingdangkou.weixin.weixiaodan.service.utils.ProductStorageGenerator;
import com.kingdangkou.weixin.weixiaodan.utils.FileHandler;
import com.kingdangkou.weixin.weixiaodan.utils.JsonHandler;
import com.kingdangkou.weixin.weixiaodan.utils.configs.ProductJsonConfig;
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
    private ProductStorageGenerator storageGenerator;
    @Autowired
    private ProductJsonConfig productJsonConfig;
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

    @Autowired
    private SubOrderEntityDao subOrderEntityDao;

    public Result get(String id){
        ProductEntity productEntity = productDao.get(ProductEntity.class, id);
        HashMap<String, Integer> sellings = subOrderEntityDao.listSoldMsg();
        ProductModel productModel = new ProductModel(productEntity, sellings.get(productEntity.getId()));
        return new Result(true, JSONObject.fromObject(productModel, productJsonConfig));
    }

    public Result list(){
        HashMap<String, Integer> sellings = subOrderEntityDao.listSoldMsg();
        List<ProductEntity> productEntities = productDao.find();
        ArrayList<ProductModel> productModels = convertToProductModelList(sellings, productEntities);
        return new Result(true, JSONArray.fromObject(productModels, productJsonConfig));
    }

    public Result list(String department){
        HashMap<String, Integer> sellings = subOrderEntityDao.listSoldMsg();
        List<ProductEntity> productEntities = productDao.find("department", department, ProductEntity.class);
        ArrayList<ProductModel> productModels = convertToProductModelList(sellings, productEntities);
        return new Result(true, JSONArray.fromObject(productModels, productJsonConfig));
    }

    private ArrayList<ProductModel> convertToProductModelList(HashMap<String, Integer> sellings, List<ProductEntity> productEntities) {
        ArrayList<ProductModel> productModels = new ArrayList<>();
        for (ProductEntity productEntity: productEntities){
            Integer sold = sellings.get(productEntity.getId());
            productModels.add(new ProductModel(productEntity, sold));
        }
        return productModels;
    }

    public Result save(ProductEntity productEntity){
        productDao.save(productEntity);
        String id = String.valueOf(productEntity.getId());
        moveFiles(productEntity.getPictures(), id);
        moveFiles(productEntity.getVideos(), id);
        return new Success();
    }

    public Result save(String name,
                       String descriptive,
                       float price,
                       String department,
                       String code,
                       int minimum,
                       String postal,
                       String pictures,
                       String videos,
                       String storageJson,
                       String label){
        ProductEntity productEntity = new ProductEntity(name, descriptive, price, code, minimum, postal, pictures, videos);

        DepartmentEntity departmentEntity = departmentDao.get(department);
        if (departmentEntity == null) return new Failure("invalid department!");
        productEntity.setDepartment(departmentEntity);

        Set<StorageEntity> storageEntitySet = storageGenerator.parse2StorageSet(storageJson);
        productEntity.setStorage(storageEntitySet);

        Set<LabelEntity> labels = convertJsonToLabelEntitySet(label);
        productEntity.setLabelEntitySet(labels);

        productEntity.setPictures(parseToString(pictures));
        productEntity.setVideos(parseToString(videos));

        productDao.save(productEntity);
        String id = String.valueOf(productEntity.getId());
        moveFiles(pictures, id);
        moveFiles(videos, id);
        return new Success();
    }

    private String parseToString(String pictures) {
        String pics = "";
        ArrayList<String> strings = JsonHandler.toArrayList(pictures);
        for (String pic: strings){
            pics += pic + ";";
        }
        return pics;
    }

    private Set<LabelEntity> convertJsonToLabelEntitySet(String label) {
        Set<LabelEntity> labels = new HashSet<>();
        JSONArray jsonArray = JSONArray.fromObject(label);
        List<LabelEntity> list = labelDao.list();
        for (Object obj: jsonArray){
            for (LabelEntity entity: list){
                if (entity.getId() == Integer.valueOf(obj.toString())){
                    labels.add(entity);
                }
            }
        }
        return labels;
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
