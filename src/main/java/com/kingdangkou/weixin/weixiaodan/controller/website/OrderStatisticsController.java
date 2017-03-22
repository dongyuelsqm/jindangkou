package com.kingdangkou.weixin.weixiaodan.controller.website;

import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.service.OrderStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dongy on 2017-03-22.
 */
@Controller
@RequestMapping(value = "/website/statistics/order")
public class OrderStatisticsController {
    @Autowired
    private OrderStatisticsService orderStatisticsService;

    @RequestMapping(value = "/date", method = RequestMethod.GET)
    public void getOrderStatisticsByOrder(@RequestParam("date") String date, HttpServletResponse response) throws IOException {
        Result statisticsByOrder = orderStatisticsService.getStatisticsByOrder(date);
        response.getWriter().print(statisticsByOrder);
    }
}
