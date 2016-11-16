package com.kingdangkou.weixin.weixiaodan.model;

import java.util.Date;

/**
 * Created by dongy on 2016-11-15.
 */

public class Order {
    private int order_id;
    private int product_id;
    private float discount;

    public Order(int order_id, int product_id, float discount, int customer_id, int address_id, Date date, int ship_id, int state, int number) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.discount = discount;
        this.customer_id = customer_id;
        this.address_id = address_id;
        this.date = date;
        this.ship_id = ship_id;
        this.state = state;
        this.number = number;
    }

    private int customer_id;
    private int address_id;
    private Date date;
    private int ship_id;
    private int state;

    public Order() {
    }

    public Order(int customer_id, int product_id, int number, int address_id) {
        this.customer_id = customer_id;
        this.product_id = product_id;
        this.number = number;
        this.address_id = address_id;
        date = new Date();
        state = 0;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    private int number;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int id) {
        this.order_id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getShip_id() {
        return ship_id;
    }

    public void setShip_id(int ship_id) {
        this.ship_id = ship_id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
