package com.kingdangkou.weixin.weixiaodan.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by dongy on 2016-12-16.
 */
@Entity
@Table(name = "colors")
@Component
public class Color {
    private int id;
    private String name;

    private Color(){}
    public Color(String name) {
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
