package com.kingdangkou.weixin.weixiaodan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by dongy on 2016-11-16.
 */
@Entity
@Table(name = "products")
public class Product {
    private int id;
    private String name;
    private String department;
    private float price;
    private String description;
    private String pic;

    public Product(String name, String department, float price, String description, String pic) {
        this.name = name;
        this.department = department;
        this.price = price;
        this.description = description;
        this.pic = pic;
    }

    @Column(name = "department_id")
    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "product_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "department_id")
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Column(name = "unit_price")
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "picture")
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
