package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by dongy on 2016-11-13.
 */
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET, value = "/order/list")
    public void listAllMyOrders(@RequestParam("openID") String openID, HttpServletResponse response) throws SQLException, IOException {
        Result result = orderService.find(openID);
        response.getWriter().print(result);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/order/add")
    public void createOrder(@RequestParam("openID") String openID,
                            @RequestParam("sub_orders") String subOrders,
                            @RequestParam("address_id") String address_id,
                            HttpServletResponse response) throws IOException {
        Result result = orderService.save(openID, subOrders, address_id);
        response.getWriter().print(result);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/order/update")
    public void update(@RequestParam("id") int id, @RequestParam("newState") String newState, HttpServletResponse response) throws IOException {
        Result result = orderService.updateState(id, newState);
        response.getWriter().print(result);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/website/list/state")
    public void findSingleState(@RequestParam("state") String state, HttpServletResponse response) throws IOException {
        Result result = orderService.findState(state);
        response.getWriter().print(result);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/order/list/state")
    public void listMyOrdersOfState(@RequestParam("openID") String openID,
                                    @RequestParam("state") String state, HttpServletResponse response) throws IOException {
        Result result = orderService.find(openID, state);
        response.getWriter().print(result);
    }
}
