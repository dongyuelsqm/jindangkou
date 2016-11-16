package com.kingdangkou.weixin.weixiaodan.model;

import javax.persistence.*;

/**
 * Created by dongy on 2016-11-16.
 */
@Entity
@Table(name = "addresses")
public class Address {
    private int id;
    private String openID;
    private String province;
    private String city;
    private String disctrict;
    private String detail;

    public Address(String openID, String province, String city, String disctrict, String detail) {
        this.openID = openID;
        this.province = province;
        this.city = city;
        this.disctrict = disctrict;
        this.detail = detail;
    }

    @Column(name = "address_id")
    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "open_id")
    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "district")
    public String getDisctrict() {
        return disctrict;
    }

    public void setDisctrict(String disctrict) {
        this.disctrict = disctrict;
    }

    @Column(name = "detail")
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
