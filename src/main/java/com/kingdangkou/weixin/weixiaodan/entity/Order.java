package com.kingdangkou.weixin.weixiaodan.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
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
    private long id;
    private float discount;
    private float method_price;
    private float actual_price;
    private CustomerEntity customerEntity;
    private Date date;
    private String ship_id;
    private int state = NOT_PAY.getValue();
    private Address address;
    private String expressNumber;
    private String weixinTransactionId;
    private Set<SubOrder> subOrders = new HashSet<SubOrder>();

    public Order() {
        this.id = System.currentTimeMillis();
        this.date = new Date();
    }

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(targetEntity = Address.class)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    @Cascade(CascadeType.ALL)
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Column(name = "method_price")
    public float getMethod_price() {
        return method_price;
    }

    public void setMethod_price(float method_price) {
        this.method_price = method_price;
    }

    @Column(name = "actual_price")
    public float getActual_price() {
        return actual_price;
    }

    public void setActual_price(float actural_price) {
        this.actual_price = actural_price;
    }

    @Column(name = "discount")
    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    @ManyToOne(targetEntity = CustomerEntity.class)
    @JoinColumn(name = "open_id", referencedColumnName = "open_id", nullable = false)
    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    @Column(name = "date")
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
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @OneToMany(targetEntity = SubOrder.class, mappedBy = "order")
    @Cascade(CascadeType.ALL)
    public Set<SubOrder> getSubOrders() {
        return subOrders;
    }

    public void setSubOrders(Set<SubOrder> subOrders) {
        for (SubOrder subOrder: subOrders){
            subOrder.setOrder(this);
        }
        this.subOrders = subOrders;
    }

    @Column(name = "express_number")
    public String getExpressNumber() {
        return expressNumber;
    }

    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber;
    }

    @Column(name = "weixin_order_id")
    public String getWeixinTransactionId() {
        return weixinTransactionId;
    }

    public void setWeixinTransactionId(String weixinTransaction_id) {
        this.weixinTransactionId = weixinTransaction_id;
    }
}
