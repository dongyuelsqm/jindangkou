package com.kingdangkou.weixin.weixiaodan.model;

import com.kingdangkou.weixin.weixiaodan.entity.DepartmentEntity;
import com.kingdangkou.weixin.weixiaodan.entity.LabelEntity;
import com.kingdangkou.weixin.weixiaodan.entity.ProductEntity;
import com.kingdangkou.weixin.weixiaodan.entity.StorageEntity;

import javax.enterprise.inject.Model;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dongy on 2017-01-08.
 */
@Model
public class ProductModel {
    private String id;
    private String name;
    private String description;
    private float price;
    private DepartmentEntity department;
    private int minimum;
    private String postal;
    private String pictures;
    private String videos;
    private String code;
    private String date;
    private int sellingQuantity;
    private Set<StorageEntity> storages = new HashSet<StorageEntity>();
    private Set<LabelEntity> labelEntitySet = new HashSet<>();

    private SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public ProductModel() {}

    public ProductModel(ProductEntity entity, Integer sellingQuantity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.department = entity.getDepartment();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.minimum = entity.getMinimum();
        this.postal = entity.getPostal();
        this.pictures = entity.getPictures();
        this.videos = entity.getVideos();
        this.code = entity.getCode();
        this.date = dateTimeFormatter.format(entity.getDate());
        this.sellingQuantity = sellingQuantity == null? 0: sellingQuantity;
        this.storages = entity.getStorage();
        this.labelEntitySet = entity.getLabelEntitySet();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getVideos() {
        return videos;
    }

    public void setVideos(String videos) {
        this.videos = videos;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSellingQuantity() {
        return sellingQuantity;
    }

    public void setSellingQuantity(int sellingQuantity) {
        this.sellingQuantity = sellingQuantity;
    }

    public Set<StorageEntity> getStorages() {
        return storages;
    }

    public void setStorages(Set<StorageEntity> storages) {
        this.storages = storages;
    }

    public Set<LabelEntity> getLabelEntitySet() {
        return labelEntitySet;
    }

    public void setLabelEntitySet(Set<LabelEntity> labelEntitySet) {
        this.labelEntitySet = labelEntitySet;
    }
}
