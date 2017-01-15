package com.kingdangkou.weixin.weixiaodan.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dongy on 2016-11-16.
 */
@Entity
@Table(name = "products")
public class ProductEntity {
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

    private Set<StorageEntity> storage = new HashSet<StorageEntity>();
    private Set<LabelEntity> labelEntitySet = new HashSet<>();

    public ProductEntity() {}

    public ProductEntity(String name, String description, float price, String code, int minimum, String postal, String pictures, String videos) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.code = code;
        this.minimum = minimum;
        this.postal = postal;
        this.pictures = pictures;
        this.videos = videos;
        this.date = new Date();
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "name")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "contains invalid chars")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "minimum")
    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    @Column(name = "postal")
    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    @Column(name = "pictures")
    public String getPictures() {
        return pictures;
    }

    public void setPictures(String prictures) {
        this.pictures = prictures;
    }


    @Column(name = "videos")
    public String getVideos() {
        return videos;
    }

    public void setVideos(String videos) {
        this.videos = videos;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String descriptive) {
        this.description = descriptive;
    }

    @ManyToOne(targetEntity = DepartmentEntity.class)
    @JoinColumn(name = "department_id", referencedColumnName = "id", nullable = false)
    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    @Column(name = "unit_price")
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Column(name = "input_date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @OneToMany(targetEntity = StorageEntity.class, mappedBy = "productEntity")
    public Set<StorageEntity> getStorage() {
        return storage;
    }

    public void setStorage(Set<StorageEntity> productQuantityEntitySet) {
        this.storage = productQuantityEntitySet;
    }
    public void addStorage(StorageEntity quantity){
        this.storage.add(quantity);
    }

    public StorageEntity getProductQuantityEntity(int color, int size){
        for (StorageEntity entity: storage){
            if (entity.getColorEntity().getId() == color && entity.getSizeEntity().getId() == size){
                return entity;
            }
        }
        return null;
    }

    @ManyToMany(targetEntity = LabelEntity.class)
    @JoinTable(name = "product_label_links", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "label_id", referencedColumnName = "id"))
    public Set<LabelEntity> getLabelEntitySet() {
        return labelEntitySet;
    }

    public void setLabelEntitySet(Set<LabelEntity> labelEntitySet) {
        this.labelEntitySet = labelEntitySet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity that = (ProductEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public static void main(String[] args) {
        Set<LabelEntity> entities = new HashSet<>();
        LabelEntity entity = new LabelEntity();
        entity.setId(1);
        entity.setTitle("2");
        entities.add(entity);

        LabelEntity target = new LabelEntity();
        target.setId(1);
        System.out.println(entities.contains(target));

    }
}
