package com.kingdangkou.weixin.weixiaodan.entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dongy on 2016-11-15.
 */
@Entity
@Table(name = "orders")
public class Order {
    private int id;
    private float discount;
    private String openID;
    @ManyToOne(targetEntity = Address.class)
    @JoinColumn(name = "address_id", nullable = false)
    @Cascade(CascadeType.ALL)
    private Address address;
    private Date date;
    private String ship_id;
    private int state;
    @OneToMany(targetEntity = SubOrder.class, mappedBy = "order_id")
    private Set<SubOrder> subOrders = new HashSet<SubOrder>();
    public Order() {}

    public Order(int id, String openID, float discount, int address_id, Date date, String ship_id, int state) {
        this.id = id;
        this.openID = openID;
        this.discount = discount;
        this.address_id = address_id;
        this.date = date;
        this.ship_id = ship_id;
        this.state = state;
    }

    public Order(String openID, int address_id) {
        this.openID = openID;
        this.address_id = address_id;
        date = new Date();
        state = 0;
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
    public String getShip_id() {
        return ship_id;
    }

    public void setShip_id(String ship_id) {
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
