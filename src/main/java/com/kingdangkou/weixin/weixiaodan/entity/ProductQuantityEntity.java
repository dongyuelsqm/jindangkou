package com.kingdangkou.weixin.weixiaodan.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by dongy on 2016-12-12.
 */
@Entity
@Table(name = "product_quantity")
@Component
public class ProductQuantityEntity {
    private Product product;
    private int number;
    private int color;
    private int size;

    public ProductQuantityEntity(Product product, int number, int color, int size) {
        this.product = product;
        this.number = number;
        this.color = color;
        this.size = size;
    }

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(name = "number")
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Column(name = "color")
    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Column(name = "size")
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
