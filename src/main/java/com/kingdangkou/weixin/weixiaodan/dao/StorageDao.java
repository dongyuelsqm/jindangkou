package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.StorageEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

/**
 * Created by dongy on 2017-01-16.
 */
@Component
public class StorageDao extends BaseDaoHibernate4<StorageEntity>{
    public void update(int product_id, int color_id, int size_id, int change){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "update StorageEntity as s set s.number = s.number - " + change +" where " + "s.productEntity.id = " + product_id +
                " and s.colorEntity.id = " + color_id + " and s.sizeEntity.id = " + size_id;
        session.createQuery(hql);
        transaction.commit();
    }
}
