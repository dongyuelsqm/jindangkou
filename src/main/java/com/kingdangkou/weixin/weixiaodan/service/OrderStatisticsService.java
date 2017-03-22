package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.OrderStatisticsDao;
import com.kingdangkou.weixin.weixiaodan.entity.DateOrderStatistics;
import com.kingdangkou.weixin.weixiaodan.entity.Order;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dongy on 2017-03-22.
 */
@Service
public class OrderStatisticsService {
    @Autowired
    private OrderStatisticsDao orderStatisticsDao;

    public Result getStatisticsByOrder(String date) {
        JSONArray json = JSONArray.fromObject(date);
        Object[] objects = json.toArray();
        HashMap<String, List<Order>> orders = orderStatisticsDao.getOrders(objects);
        List<DateOrderStatistics> statisticss = convertToDateOrderStatisticss(orders);
        return new Result(true, statisticss);
    }

    private List<DateOrderStatistics> convertToDateOrderStatisticss(HashMap<String, List<Order>> orders) {
        List<DateOrderStatistics> statisticss = new ArrayList<>();
        for (Map.Entry<String, List<Order>> entry: orders.entrySet()){
            DateOrderStatistics statistics = new DateOrderStatistics();
            statistics.setTime(entry.getKey());
            statistics.setOrderNumber(entry.getValue().size());
            int total = 0;
            List<String> customers = new ArrayList<>();
            for (Order order: entry.getValue()){
                total += order.getActual_price();
                String openID = order.getCustomerEntity().getOpenID();
                if (customers.contains(openID)) continue;
                customers.add(openID);
            }
            statistics.setCustomerNumber(customers.size());
            statistics.setSales(total);
            statisticss.add(statistics);
        }
        return statisticss;
    }
}
