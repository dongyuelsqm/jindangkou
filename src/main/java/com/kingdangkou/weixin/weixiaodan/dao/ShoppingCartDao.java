package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.TobePurchasedProductEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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
        String hql = "delete from TobePurchasedProductEntity where openID = " + openId + " and (";
        for (Integer id: ids){
            hql += " id = " + id + " or ";
        }
        hql = hql.substring(0, hql.lastIndexOf("or"));
        hql += ")";
        Query query = session.createQuery(hql);
        query.executeUpdate();
        transaction.commit();
    }
}
