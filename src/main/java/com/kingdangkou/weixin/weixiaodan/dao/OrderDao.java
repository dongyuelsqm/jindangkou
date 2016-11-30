package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dongy on 2016-11-18.
 */
@Component
public class OrderDao extends BaseDaoHibernate4<Order>{
    public List<Order> findAllOrders(String openID){
        return find("openID", openID, Order.class);
    }

    public Order getOrder(String key){
        return get(Order.class, key, "order_id");
    }

    public List<Order> listOrdersByState(String state){
        return find("state", state, Order.class);
    }
}
