package com.kingdangkou.weixin.weixiaodan.entity;

import net.sf.json.JSONObject;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by dongy on 2017-01-12.
 */
@Entity
@Table(name = "customers")
public class CustomerEntity implements Serializable{

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "open_id")
    private String openID;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "sex")
    private int sex;
    @Column(name = "province")
    private String province;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
    @Column(name = "headimgurl")
    private String headimgurl;
    @Column(name = "privilege")
    private String privilege;
    @Column(name = "unionid")
    private String unionid;
    @Column(name = "access_time")
    private Date accessTime;


    public CustomerEntity() {}

    public CustomerEntity(JSONObject jsonObject) {
        this.openID = jsonObject.getString("openid");
        this.nickname = jsonObject.getString("nickname");
        this.sex = jsonObject.getInt("sex");
        this.province = jsonObject.getString("province");
        this.city = jsonObject.getString("city");
        this.country = jsonObject.getString("country");
        this.headimgurl = jsonObject.getString("headimgurl");
        this.privilege = jsonObject.getString("privilege");
        this.unionid = jsonObject.getString("unionid");
        this.accessTime = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }
}
