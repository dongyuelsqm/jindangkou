package com.kingdangkou.weixin.weixiaodan.entity;

import javax.persistence.*;

/**
 * Created by dongy on 2017-01-06.
 */
@Entity
@Table(name = "labels")
public class LabelEntity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    public LabelEntity() {
    }

    public LabelEntity(String title) {
        this.title = title;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LabelEntity entity = (LabelEntity) o;

        return this.id == entity.id;
    }

    @Override
    public int hashCode() {
        int result = id;
        return result;
    }
}
