package com.kingdangkou.weixin.weixiaodan.controller.website;

import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dongy on 2017-01-18.
 */
@Controller
@RequestMapping("/website")
public class WebSiteOrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/order/date/list", method = RequestMethod.GET)
    public void getOrdersByDate(@RequestParam("begin") String begin, @RequestParam("end") String end, HttpServletResponse response) throws IOException {
        Result orderByDate = orderService.getOrderByDate(begin, end);
        response.getWriter().print(orderByDate);
    }

    @RequestMapping(value = "/order/list/all", method = RequestMethod.GET)
    public void getAllOrders(HttpServletResponse response) throws IOException {
        Result list = orderService.list();
        response.getWriter().print(list);
    }

    @RequestMapping(value = "/order/add", method = RequestMethod.POST)
    public void addOrder(@RequestParam("name") String name, @RequestParam("sub_orders") String sub_orders, @RequestParam("address") String address, HttpServletResponse response) throws IOException {
        Result result = orderService.addOrder(name, sub_orders, address);
        response.getWriter().print(result);
    }
}
