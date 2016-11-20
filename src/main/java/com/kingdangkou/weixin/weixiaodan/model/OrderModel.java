package com.kingdangkou.weixin.weixiaodan.model;

import javax.enterprise.inject.Model;
import java.util.Date;

/**
 * Created by dongy on 2016-11-20.
 */
@Model
public class OrderModel {
    private String id;
    private String productName;

    private float unitPrice;

    private float totalPrice;
    private String address;
    private String shipNumber;
    private String state;
    private int number;
    private Date date;

    public OrderModel() {}

    public OrderModel(String id, String productName, float unitPrice, int number, float totalPrice, String address, String shipNumber, String state, Date date) {
        this.id = id;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.address = address;
        this.shipNumber = shipNumber;
        this.state = state;
        this.number = number;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShipNumber() {
        return shipNumber;
    }

    public void setShipNumber(String shipNumber) {
        this.shipNumber = shipNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
