package com.kingdangkou.weixin.weixiaodan.model;

import com.kingdangkou.weixin.weixiaodan.entity.Address;
import com.kingdangkou.weixin.weixiaodan.entity.Order;
import com.kingdangkou.weixin.weixiaodan.entity.Product;

import javax.enterprise.inject.Model;
import java.util.Date;

/**
 * Created by dongy on 2016-11-20.
 */
@Model
public class OrderModel {
    private String id;
    private String productName;
    private String addressee;
    private String contacts;
    private float unitPrice;
    private float totalPrice;
    private String address;
    private String shipNumber;
    private int state;
    private int number;
    private Date date;

    public OrderModel() {}

    public OrderModel(Order order, Product product, Address address){
        id = String.valueOf(order.getOrder_id());
        this.addressee = address.getName();
        this.contacts = address.getPhone();
        this.productName = product.getName();
        this.unitPrice = product.getPrice();
        this.totalPrice = product.getPrice();
        this.address = getAddress(address);
        this.shipNumber = order.getShip_id();
        this.state = order.getState();
        this.date = order.getDate();
    }

    private String getAddress(Address address) {
        return address.getProvince() + address.getCity() + address.getDisctrict() + address.getDetail();
    }

    public OrderModel(String id, String productName, float unitPrice, int number, float totalPrice, String address, String shipNumber, int state, Date date) {
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
    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
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
