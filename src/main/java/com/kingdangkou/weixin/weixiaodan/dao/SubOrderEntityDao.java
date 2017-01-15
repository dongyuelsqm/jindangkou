package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.SubOrder;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by dongy on 2017-01-08.
 */
@Component
public class SubOrderEntityDao extends BaseDaoHibernate4<SubOrder>{
    public HashMap<String, Integer> listSoldMsg(){
        List list = quaryFromDB();
        return convert2HashMap(list);
    }

    private List quaryFromDB() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String queryString = "select s.productEntity.id, sum(s.number) from SubOrder as s group by s.productEntity.id";
        List list = session.createQuery(queryString).list();
        transaction.commit();
        session.close();
        return list;
    }

    private HashMap<String, Integer> convert2HashMap(List list) {
        HashMap<String, Integer> sells= new HashMap<>();
        for (Object obj: list){
            Object[] sold = (Object[]) obj;
            sells.put(sold[0].toString(), Integer.valueOf(sold[1].toString()));
        }
        return sells;
    }
}
