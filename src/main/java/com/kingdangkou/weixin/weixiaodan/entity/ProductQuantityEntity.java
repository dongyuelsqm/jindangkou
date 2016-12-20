package com.kingdangkou.weixin.weixiaodan.entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import static org.hibernate.annotations.CascadeType.ALL;


/**
 * Created by dongy on 2016-12-12.
 */
@Entity
@Table(name = "product_quantity")
public class ProductQuantityEntity {
    private int id;
    private Product product;
    private ColorEntity colorEntity;
    private SizeEntity sizeEntity;
    private int number;

    public ProductQuantityEntity() {}

    public ProductQuantityEntity(Product product, ColorEntity colorEntity, SizeEntity sizeEntity, int number) {
        this.product = product;
        this.colorEntity = colorEntity;
        this.sizeEntity = sizeEntity;
        this.number = number;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int key) {
        this.id = key;
    }

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
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

    @ManyToOne(targetEntity = ColorEntity.class)
    @JoinColumn(name = "color", referencedColumnName = "id",nullable = false)
    @Cascade(value = ALL)
    public ColorEntity getColorEntity() {
        return colorEntity;
    }

    public void setColorEntity(ColorEntity colorEntity) {
        this.colorEntity = colorEntity;
    }

    @ManyToOne(targetEntity = SizeEntity.class)
    @JoinColumn(name = "size", referencedColumnName = "id",nullable = false)
    @Cascade(value = ALL)
    public SizeEntity getSizeEntity() {
        return sizeEntity;
    }

    public void setSizeEntity(SizeEntity size) {
        this.sizeEntity = size;
    }


}
