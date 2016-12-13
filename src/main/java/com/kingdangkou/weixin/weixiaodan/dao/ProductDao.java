package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.Product;
import com.kingdangkou.weixin.weixiaodan.entity.ProductQuantityEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dongy on 2016-11-18.
 */
@Component
public class ProductDao extends BaseDaoHibernate4<Product>  {
    public List<Product> find(){
        return find("select * from Product");
    }

    public Product get(String productID){
        return get(Product.class, productID);
    }

    public int getQuantity(String id, int color, int size){
        Product product = get(Product.class, id);
        for (ProductQuantityEntity entity: product.getProductQuantityEntitys()){
            if (entity.getColor() == color && entity.getSize() == size)
                return entity.getNumber();
        }
        return 0;
    }

    public void updateQuantity(String id, int color, int size, int number){
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
}
