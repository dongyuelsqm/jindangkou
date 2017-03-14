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
    private String provinceName;
    private String cityName;
    private String expAreaName;
    private String address;
    private String name;
    private String mobile;

    public Address() {}

    public Address(String id, String name, String mobile, String openID, String provinceName, String cityName, String expAreaName, String address) {
        this.id = Integer.valueOf(id);
        this.openID = openID;
        this.provinceName = provinceName;
        this.cityName = cityName;
        this.expAreaName = expAreaName;
        this.address = address;
        this.name = name;
        this.mobile = mobile;
    }

    public Address(String name, String mobile, String openID, String provinceName, String cityName, String expAreaName, String address) {
        this.name = name;
        this.mobile = mobile;
        this.openID = openID;
        this.provinceName = provinceName;
        this.cityName = cityName;
        this.expAreaName = expAreaName;
        this.address = address;
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

    @Size(min = 1, max = 10, message = "provinceName can not be run")
    @Column(name = "provinceName")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "contains invalid chars")
    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    @Column(name = "cityName")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "contains invalid chars")
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Column(name = "district")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "contains invalid chars")
    public String getExpAreaName() {
        return expAreaName;
    }

    public void setExpAreaName(String expAreaName) {
        this.expAreaName = expAreaName;
    }

    @Column(name = "address")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "contains invalid chars")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "name")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "contains invalid chars")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "mobile")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "contains invalid chars")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (provinceName.equals(address.provinceName) || cityName.equals(address.cityName) || expAreaName.equals(address.expAreaName) || this.address.equals(address.address)) return false;

        return true;
    }
}
