package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.SubOrder;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dongy on 2017-01-08.
 */
@Component
public class SubOrderEntityDao extends BaseDaoHibernate4<SubOrder>{
    public List<Object[]> listSellingMsg(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String queryString = "select s.productEntity, sum(s.number) from SubOrder as s group by s.productEntity";
        List list = session.createQuery(queryString).list();
        transaction.commit();
        session.close();
        return list;
    }



    public static void main(String[] args) {
        SubOrderEntityDao dao = new SubOrderEntityDao();
        dao.listSellingMsg();
    }
}
