package com.kingdangkou.weixin.weixiaodan.model;

import com.kingdangkou.weixin.weixiaodan.entity.DepartmentEntity;
import com.kingdangkou.weixin.weixiaodan.entity.LabelEntity;
import com.kingdangkou.weixin.weixiaodan.entity.ProductQuantityEntity;

import javax.enterprise.inject.Model;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dongy on 2017-01-08.
 */
@Model
public class Product {
    private String id;
    private String name;
    private String description;
    private float price;
    private DepartmentEntity department;
    private int minimum;
    private String postal;
    private String pictures;
    private String videos;
    private String code;
    private Date date;

    private Set<ProductQuantityEntity> productQuantityEntitys = new HashSet<ProductQuantityEntity>();
    private Set<LabelEntity> labelEntitySet = new HashSet<>();
}
