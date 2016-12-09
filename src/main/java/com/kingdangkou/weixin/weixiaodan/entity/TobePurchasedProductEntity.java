package com.kingdangkou.weixin.weixiaodan.entity;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

/**
 * Created by dongy on 2016-12-07.
 */
@Entity
public class TobePurchasedProductEntity {
    private int id;

    private String openID;
    private String product_id;
    private String color;
    private String number;
    private String size;
    public TobePurchasedProductEntity() {}

    public TobePurchasedProductEntity(String openID, String product_id, String color, String number, String size) {
        this.openID = openID;
        this.product_id = product_id;
        this.color = color;
        this.number = number;
        this.size = size;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Column(name = "openID")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "contains invalid chars")
    @NotNull
    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
