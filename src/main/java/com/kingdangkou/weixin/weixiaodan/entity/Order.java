package com.kingdangkou.weixin.weixiaodan.entity;

import com.kingdangkou.weixin.weixiaodan.enums.OrderStateEnum;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static com.kingdangkou.weixin.weixiaodan.enums.OrderStateEnum.NOT_PAY;

/**
 * Created by dongy on 2016-11-15.
 */
@Entity
@Table(name = "orders")
public class Order {
    private int id;
    private float discount;
    private String openID;
    private Date date;
    private String ship_id;
    private int state = NOT_PAY;
    private Address address;

    private Set<SubOrder> subOrders = new HashSet<SubOrder>();

    public Order() {}

    public Order(String openID) {
        this.openID = openID;
        date = new Date();
        state = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "contains invalid chars")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Pattern(regexp = "[a-zA-Z0-9]+", message = "contains invalid chars")
    @ManyToOne(targetEntity = Address.class)
    @JoinColumn(name = "address_id", nullable = false)
    @Cascade(CascadeType.ALL)
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    @Column(name = "discount")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "contains invalid chars")
    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    @Column(name = "open_id")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "contains invalid chars")
    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    @Column(name = "deal_date")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "contains invalid chars")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "ship_id")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "contains invalid chars")
    public String getShip_id() {
        return ship_id;
    }

    public void setShip_id(String ship_id) {
        this.ship_id = ship_id;
    }

    @Column(name = "state")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "contains invalid chars")
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @OneToMany(targetEntity = SubOrder.class, mappedBy = "order")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "contains invalid chars")
    public Set<SubOrder> getSubOrders() {
        return subOrders;
    }

    public void setSubOrders(Set<SubOrder> subOrders) {
        this.subOrders = subOrders;
    }
}
