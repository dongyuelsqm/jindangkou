package com.kingdangkou.weixin.weixiaodan.entity;

import javax.persistence.*;

/**
 * Created by dongy on 2016-11-28.
 */
@Entity
@Table(name = "sub_orders")
public class SubOrder {

    private int id;

    private Order order;


    private ProductEntity productEntity;


    private int number;

    private SizeEntity size;


    private ColorEntity color;

    public SubOrder() {}

    public SubOrder(Order order, ProductEntity productEntity, ColorEntity colorEntity, SizeEntity sizeEntity, Integer number) {
        this.order = order;
        this.productEntity = productEntity;
        this.color = colorEntity;
        this.size = sizeEntity;
        this.number = number;
    }

    public SubOrder(Order order) {
        this.order = order;
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

    @Column(name = "number")
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @OneToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id", unique = true)
    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    @ManyToOne(targetEntity = SizeEntity.class)
    @JoinColumn(name = "size_id", referencedColumnName = "id", nullable = false)
    public SizeEntity getSize() {
        return size;
    }

    public void setSize(SizeEntity size) {
        this.size = size;
    }

    @ManyToOne(targetEntity = ColorEntity.class)
    @JoinColumn(name = "color_id", referencedColumnName = "id", nullable = false)
    public ColorEntity getColor() {
        return color;
    }

    public void setColor(ColorEntity color) {
        this.color = color;
    }
}
