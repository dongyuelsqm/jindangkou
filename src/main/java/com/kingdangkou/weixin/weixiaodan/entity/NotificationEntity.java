package com.kingdangkou.weixin.weixiaodan.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dongy on 2017-01-10.
 */
@Entity
@Table(name = "notifications")
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "created_at")
    private Date date;

    public NotificationEntity() {}

    public NotificationEntity(String title) {
        this.title = title;
        this.date = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
