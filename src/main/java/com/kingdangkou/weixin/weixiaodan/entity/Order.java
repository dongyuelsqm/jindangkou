package com.kingdangkou.weixin.weixiaodan.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dongy on 2016-11-15.
 */
@Entity
@Table(name = "orders")
public class Order {
    private int id;
    private int product_id;
    private float discount;
    private String openID;
    private int address_id;
    private Date date;
    private int ship_id;
    private int number;
    private int state;

    public Order() {}

    public Order(int id, String openID, int product_id, float discount, int address_id, Date date, int ship_id, int state, int number) {
        this.id = id;
        this.openID = openID;
        this.product_id = product_id;
        this.discount = discount;
        this.address_id = address_id;
        this.date = date;
        this.ship_id = ship_id;
        this.state = state;
        this.number = number;
    }

    public Order(String openID, int product_id, int number, int address_id) {
        this.openID = openID;
        this.product_id = product_id;
        this.number = number;
        this.address_id = address_id;
        date = new Date();
        state = 0;
    }

    @Column(name = "number")
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    public int getOrder_id() {
        return id;
    }

    public void setOrder_id(int id) {
        this.id = id;
    }

    @Column(name = "product_id")
    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    @Column(name = "discount")
    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    @Column(name = "open_id")
    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    @Column(name = "address_id")
    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    @Column(name = "deal_date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "ship_id")
    public int getShip_id() {
        return ship_id;
    }

    public void setShip_id(int ship_id) {
        this.ship_id = ship_id;
    }

    @Column(name = "state")
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
