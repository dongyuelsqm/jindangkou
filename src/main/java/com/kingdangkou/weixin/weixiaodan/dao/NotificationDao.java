package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.NotificationEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

/**
 * Created by dongy on 2017-01-10.
 */
@Component
public class NotificationDao extends BaseDaoHibernate4<NotificationEntity>{

    public NotificationEntity getLatest() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Object o = session.createQuery("from  NotificationEntity where date = (select max (s.date) from NotificationEntity s)").uniqueResult();
        NotificationEntity list = (NotificationEntity) o;
        transaction.commit();
        return list;
    }
}
