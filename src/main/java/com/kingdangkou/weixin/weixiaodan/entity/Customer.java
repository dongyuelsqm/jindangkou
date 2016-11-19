package com.kingdangkou.weixin.weixiaodan.entity;

import javax.persistence.*;

/**
 * Created by dongy on 2016-11-16.
 */
@Entity
@Table(name = "customers")
public class Customer {

    private String id;
    private String name;
    private String gender;
    private String phone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (!id.equals(customer.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public Customer() {
    }

    public Customer(String openID, String name, String gender, String phone) {
        this.id = openID;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
    }

    @Id
    @Column(name = "open_id")
    public String getId() {
        return id;
    }

    public void setId(String openID) {
        this.id = openID;
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
