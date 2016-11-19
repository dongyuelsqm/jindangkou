package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dongy on 2016-11-18.
 */
@Component
public class OrderDao extends BaseDaoHibernate4<Order>{
    public List<Order> findOrders(String openID){
        return find("From Order where openID = " + openID);
    }

    public Order getOrder(String key){
        return get(Order.class, key);
    }
}
