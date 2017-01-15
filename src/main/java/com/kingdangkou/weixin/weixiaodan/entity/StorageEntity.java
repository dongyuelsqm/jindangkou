package com.kingdangkou.weixin.weixiaodan.entity;

import javax.persistence.*;


/**
 * Created by dongy on 2016-12-12.
 */
@Entity
@Table(name = "product_quantity")
public class StorageEntity {
    private int id;
    private ProductEntity productEntity;
    private ColorEntity colorEntity;
    private SizeEntity sizeEntity;
    private int number;

    public StorageEntity() {}

    public StorageEntity(ProductEntity productEntity, ColorEntity colorEntity, SizeEntity sizeEntity, int number) {
        this.productEntity = productEntity;
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
    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
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
    public ColorEntity getColorEntity() {
        return colorEntity;
    }

    public void setColorEntity(ColorEntity colorEntity) {
        this.colorEntity = colorEntity;
    }

    @ManyToOne(targetEntity = SizeEntity.class)
    @JoinColumn(name = "size", referencedColumnName = "id",nullable = false)
    public SizeEntity getSizeEntity() {
        return sizeEntity;
    }

    public void setSizeEntity(SizeEntity size) {
        this.sizeEntity = size;
    }


}
