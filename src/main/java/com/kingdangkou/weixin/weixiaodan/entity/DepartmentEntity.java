package com.kingdangkou.weixin.weixiaodan.entity;

import javax.persistence.*;

/**
 * Created by dongy on 2016-12-22.
 */
@Entity
@Table(name = "departments")
public class DepartmentEntity {
    private int id;
    private String name;

    public DepartmentEntity() {}

    public DepartmentEntity(String name) {
        this.name = name;
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

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
