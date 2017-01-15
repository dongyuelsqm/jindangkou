package com.kingdangkou.weixin.weixiaodan.entity;

import javax.persistence.*;

/**
 * Created by dongy on 2016-12-07.
 */
@Entity
@Table(name = "shoping_cart")
public class TobePurchasedProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "open_id")
    private String openID;

    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id", unique = true)
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "color_id",referencedColumnName = "id", unique = true)
    private ColorEntity color;

    @ManyToOne
    @JoinColumn(name = "size_id",referencedColumnName = "id", unique = true)
    private SizeEntity size;

    @Column(name = "number")
    private int number;
    public TobePurchasedProductEntity() {}

    public TobePurchasedProductEntity(String openID, ProductEntity productEntity, ColorEntity color, SizeEntity size, int number) {
        this.openID = openID;
        this.productEntity = productEntity;
        this.color = color;
        this.size = size;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }


    public ColorEntity getColor() {
        return color;
    }

    public void setColor(ColorEntity color) {
        this.color = color;
    }


    public SizeEntity getSize() {
        return size;
    }

    public void setSize(SizeEntity size) {
        this.size = size;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
