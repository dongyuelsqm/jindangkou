package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.TobePurchasedProductEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dongy on 2016-12-07.
 */
@Component
public class ShoppingCartDao extends BaseDaoHibernate4<TobePurchasedProductEntity> {

    public TobePurchasedProductEntity get(String id){
        return get(TobePurchasedProductEntity.class, id);
    }

    public void delete(List<Integer> ids, String openId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "delete from TobePurchasedProductEntity where id = " + ids.get(0);
        for (Integer id: ids){
            hql += " or id = " + id;
        }
        session.createQuery(hql).executeUpdate();
        transaction.commit();
        session.close();
    }
}
