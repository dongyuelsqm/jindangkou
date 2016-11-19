package com.kingdangkou.weixin.weixiaodan.entity;

import javax.persistence.*;

/**
 * Created by dongy on 2016-11-16.
 */
@Entity
@Table(name = "products")
public class Product {
    private int id;
    private String name;
    private int department;
    private float price;

    private String description;

    private String pic;
    public Product(String name, int department, float price, String description, String pic) {
        this.name = name;
        this.department = department;
        this.price = price;
        this.description = description;
        this.pic = pic;
    }

    public Product(String name, int department, float price, String description) {
        this.name = name;
        this.department = department;
        this.price = price;
        this.description = description;
    }

    public Product() {}

    @Id
    @Column(name = "product_id")
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
    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;

        return true;
    }
}
