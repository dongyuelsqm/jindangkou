package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.entity.Order;
import com.kingdangkou.weixin.weixiaodan.model.ListResult;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.service.OrderService;
import com.kingdangkou.weixin.weixiaodan.utils.configs.OrderJsonConfig;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dongy on 2016-11-13.
 */
@Controller
public class OrderController {

    @Autowired
    private OrderJsonConfig orderJsonConfig;
    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET, value = "/order/list")
    public void doGet(@RequestParam("openID") String openID, HttpServletResponse response) throws SQLException, IOException {
        ListResult result = orderService.find(openID);
        response.getWriter().print(JSONArray.fromObject(result, orderJsonConfig));
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

    @RequestMapping(method = RequestMethod.GET, value = "/list/state")
    public void listOrders(@RequestParam("openID") String openID,
                           @RequestParam("State") String state, HttpServletResponse response) throws IOException {
        List<Order> orders = orderService.find(openID, state);
        response.getWriter().print(JSONArray.fromObject(orders).toString());
    }
}
