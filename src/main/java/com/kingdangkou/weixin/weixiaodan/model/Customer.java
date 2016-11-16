package com.kingdangkou.weixin.weixiaodan.model;

import javax.persistence.*;

/**
 * Created by dongy on 2016-11-16.
 */
@Entity
@Table(name = "customers")
public class Customer {

    private String openID;
    private String name;
    private String gender;
    private String phone;

    public Customer(String openID, String name, String gender, String phone) {
        this.openID = openID;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
    }

    @Id
    @GeneratedValue
    @Column(name = "open_id")
    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
