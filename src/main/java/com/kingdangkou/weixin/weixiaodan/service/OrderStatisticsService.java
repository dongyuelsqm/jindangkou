package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.OrderStatisticsDao;
import com.kingdangkou.weixin.weixiaodan.entity.OrderStatisticsData;
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

    public Result getStatisticsByDate(String date) {
        JSONArray json = JSONArray.fromObject(date);
        Object[] objects = json.toArray();
        HashMap<String, List<Order>> orders = orderStatisticsDao.getOrders(objects);
        List<OrderStatisticsData> statisticss = convertToDateOrderStatisticss(orders);
        return new Result(true, statisticss);
    }

    public Result getStatisticsByDistrict(){
        List<Object[]> list = orderStatisticsDao.getOrdersByProvince();
        List<OrderStatisticsData> formedDatas = new ArrayList<>();
        for (Object[] statics: list){
            formedDatas.add(new OrderStatisticsData(statics[2].toString(), Integer.valueOf(statics[0].toString()), Integer.valueOf(statics[0].toString()), Integer.valueOf(statics[0].toString()) ));
        }
        return new Result(true, formedDatas);
    }

    private List<OrderStatisticsData> convertToDateOrderStatisticss(HashMap<String, List<Order>> orders) {
        List<OrderStatisticsData> statisticss = new ArrayList<>();
        for (Map.Entry<String, List<Order>> entry: orders.entrySet()){
            int total = 0;
            List<String> customers = new ArrayList<>();
            for (Order order: entry.getValue()){
                total += order.getActual_price();
                String openID = order.getCustomerEntity().getOpenID();
                if (customers.contains(openID)) continue;
                customers.add(openID);
            }
            statisticss.add(new OrderStatisticsData(entry.getKey(), entry.getValue().size(), total, customers.size()));
        }
        return statisticss;
    }
}
