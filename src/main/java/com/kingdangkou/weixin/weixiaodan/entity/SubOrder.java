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

    @ManyToOne(targetEntity = Order.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Order order;

    @OneToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id", unique = true)
    private ProductEntity productEntity;

    @Column(name = "number")
    private int number;

    @ManyToOne(targetEntity = SizeEntity.class)
    @JoinColumn(name = "size_id", referencedColumnName = "id", nullable = false)
    private SizeEntity size;

    @ManyToOne(targetEntity = ColorEntity.class)
    @JoinColumn(name = "color_id", referencedColumnName = "id", nullable = false)
    private ColorEntity color;

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

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public SizeEntity getSize() {
        return size;
    }

    public void setSize(SizeEntity size) {
        this.size = size;
    }

    public ColorEntity getColor() {
        return color;
    }

    public void setColor(ColorEntity color) {
        this.color = color;
    }
}
