package com.kingdangkou.weixin.weixiaodan.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dongy on 2016-11-16.
 */
@Entity
@Table(name = "products")
public class Product {
    private String id;
    private String name;
    private String descriptive;
    private float price;
    private String department;
    private int minimum;
    private String postal;
    private String pictures;
    private String videos;
    private String code;
    private Date date;

    private Set<ProductQuantityEntity> productQuantityEntitys = new HashSet<ProductQuantityEntity>();

    public Product() {}

    public Product(String name, String descriptive, float price, String department, String code, int minimum, String postal, String pictures, String videos) {
        this.name = name;
        this.descriptive = descriptive;
        this.price = price;
        this.department = department;
        this.code = code;
        this.minimum = minimum;
        this.postal = postal;
        this.pictures = pictures;
        this.videos = videos;
        this.date = new Date();
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


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "name")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "contains invalid chars")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptive() {
        return descriptive;
    }

    public void setDescriptive(String descriptive) {
        this.descriptive = descriptive;
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

    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @OneToMany(targetEntity = ProductQuantityEntity.class, mappedBy = "product")
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
