package com.kingdangkou.weixin.weixiaodan.entity;

import javax.persistence.*;

/**
 * Created by dongy on 2016-11-28.
 */
@Entity
@Table(name = "sub_orders")
public class SubOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Order order;

    @OneToOne
    @JoinColumn(name = "product_id",referencedColumnName = "product_id", unique = true)
    private Product product;

    @Column(name = "number")
    private int number;

    @Column(name = "size")
    private int size;

    @Column(name = "color")
    private String color;

    public SubOrder() {
    }

    public SubOrder(Order order) {
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
