package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongy on 2016-11-18.
 */
@Component
public class ProductDao extends BaseDaoHibernate4<ProductEntity>  {
    public List<ProductEntity> find(){
        return find("from ProductEntity product");
    }

    public ProductEntity get(String productID){
        return get(ProductEntity.class, productID);
    }

    public int getQuantity(String id, String color, String size){
        ProductEntity productEntity = get(ProductEntity.class, id);

        StorageEntity productQuantityEntity = productEntity.getProductQuantityEntity(Integer.valueOf(color), Integer.valueOf(size));
        return productQuantityEntity == null? 0:productQuantityEntity.getNumber();
    }

    public void updateQuantity(String id, ColorEntity color, SizeEntity size, int number){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ProductEntity productEntity = get(ProductEntity.class, id);
        StorageEntity entity = new StorageEntity(productEntity, color, size, number);
        session.update(entity);
        transaction.commit();
        session.close();
    }

    public void save(ProductEntity productEntity){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(productEntity);
        for (StorageEntity quantity: productEntity.getProductQuantityEntitys()){
            quantity.setProductEntity(productEntity);
            session.persist(quantity);
        }
        transaction.commit();
        session.close();
    }

    public List<ProductEntity> findByLabels(int labelId){
        List<ProductEntity> productEntities = find();
        List<ProductEntity> productsWithLabel = new ArrayList<>();
        LabelEntity target = new LabelEntity();
        target.setId(labelId);
        for (ProductEntity productEntity : productEntities){
            if (productEntity.getLabelEntitySet().contains(target))
                productsWithLabel.add(productEntity);
        }
        return productsWithLabel;
    }

    public List<ProductEntity> findByLabels(String label){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List title_id = session.createQuery("select distinct p from ProductEntity p join p.labelEntitySet label where label.id = :title_id").setString("title_id", label).list();
        transaction.commit();
        session.close();
        return title_id;

    }
}
