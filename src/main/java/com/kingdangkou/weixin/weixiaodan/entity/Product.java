package com.kingdangkou.weixin.weixiaodan.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dongy on 2016-11-16.
 */
@Entity
@Table(name = "products")
public class Product {
    private int id;
    private String name;
    private float price;
    private String department;
    private int minimum;
    private String postal;
    private String pictures;
    private String videos;
    private String code;

    private Set<ProductQuantityEntity> productQuantityEntitys = new HashSet<ProductQuantityEntity>();

    public Product(int id, String name, float price, String department, int minimum, String postal, String pictures, String videos, String code) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.department = department;
        this.minimum = minimum;
        this.postal = postal;
        this.pictures = pictures;
        this.videos = videos;
        this.code = code;
    }

    public Product(String name, float price, String department, String code, int minimum, String postal, String pictures, String videos) {
        this.name = name;
        this.price = price;
        this.department = department;
        this.code = code;
        this.minimum = minimum;
        this.postal = postal;
        this.pictures = pictures;
        this.videos = videos;
    }

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "minimum")
    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    @Column(name = "postal")
    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    @Column(name = "pictures")
    public String getPictures() {
        return pictures;
    }

    public void setPictures(String prictures) {
        this.pictures = prictures;
    }

    @Column(name = "videos")
    public String getVideos() {
        return videos;
    }

    public void setVideos(String videos) {
        this.videos = videos;
    }

    public Product() {}


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "product_name")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "contains invalid chars")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "department_id")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "contains invalid chars")
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Column(name = "unit_price")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "contains invalid chars")
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @OneToMany(targetEntity = ProductQuantityEntity.class, mappedBy = "order")
    public Set<ProductQuantityEntity> getProductQuantityEntitys() {
        return productQuantityEntitys;
    }

    public void setProductQuantityEntitys(Set<ProductQuantityEntity> productQuantityEntitySet) {
        this.productQuantityEntitys = productQuantityEntitySet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;

        return true;
    }
}
