package com.kingdangkou.weixin.weixiaodan.entity;

import com.sun.istack.internal.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by dongy on 2016-11-16.
 */
@Entity
@Table(name = "addresses")
@Component
public class Address {
    private int id;
    private String openID;
    private String province;
    private String city;
    private String disctrict;
    private String detail;
    private String name;
    private String phone;

    public Address() {}

    public Address(String name, String phone, String openID, String province, String city, String disctrict, String detail) {
        this.name = name;
        this.phone = phone;
        this.openID = openID;
        this.province = province;
        this.city = city;
        this.disctrict = disctrict;
        this.detail = detail;
    }

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    @Column(name = "open_id")
    @NotEmpty
    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    @Size(min = 1, max = 10, message = "province can not be run")
    @Column(name = "province")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "contains invalid chars")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Column(name = "city")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "contains invalid chars")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "district")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "contains invalid chars")
    public String getDisctrict() {
        return disctrict;
    }

    public void setDisctrict(String disctrict) {
        this.disctrict = disctrict;
    }

    @Column(name = "detail")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "contains invalid chars")
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Column(name = "name")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "contains invalid chars")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "phone")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "contains invalid chars")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (province.equals(address.province) || city.equals(address.city) || disctrict.equals(address.disctrict) || detail.equals(address.detail)) return false;

        return true;
    }
}
