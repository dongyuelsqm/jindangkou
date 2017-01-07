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
public class ProductDao extends BaseDaoHibernate4<Product>  {
    public List<Product> find(){
        return find("from Product product");
    }

    public Product get(String productID){
        return get(Product.class, productID);
    }

    public int getQuantity(String id, String color, String size){
        Product product = get(Product.class, id);

        ProductQuantityEntity productQuantityEntity = product.getProductQuantityEntity(Integer.valueOf(color), Integer.valueOf(size));
        return productQuantityEntity == null? 0:productQuantityEntity.getNumber();
    }

    public void updateQuantity(String id, ColorEntity color, SizeEntity size, int number){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Product product = get(Product.class, id);
        ProductQuantityEntity entity = new ProductQuantityEntity(product, color, size, number);
        session.update(entity);
        transaction.commit();
        session.close();
    }

    public void save(Product product){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(product);
        for (ProductQuantityEntity quantity: product.getProductQuantityEntitys()){
            quantity.setProduct(product);
            session.persist(quantity);
        }
        transaction.commit();
        session.close();
    }

    public List<Product> findByLabels(int labelId){
        List<Product> products = find();
        List<Product> productsWithLabel = new ArrayList<>();
        LabelEntity target = new LabelEntity();
        target.setId(labelId);
        for (Product product: products){
            if (product.getLabelEntitySet().contains(target))
                productsWithLabel.add(product);
        }
        return productsWithLabel;
    }
}
