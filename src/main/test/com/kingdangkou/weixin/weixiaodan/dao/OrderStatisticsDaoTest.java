package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by dongy on 2017-03-22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spitter-servlet.xml")
public class OrderStatisticsDaoTest {
    @Autowired
    private OrderStatisticsDao orderStatisticsDao;
    @Test
    public void getOrders() throws Exception {
        String[] date = new String[]{"2017-03-12"};
        HashMap<String, List<Order>> orders = orderStatisticsDao.getOrders(date);
        assertEquals("sddfdfdfdf", orders.get("2017-03-12").get(0).getWeixinTransactionId());
    }

}