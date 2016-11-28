package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.model.OrderModel;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.service.OrderService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dongy on 2016-11-13.
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        List<OrderModel> orders = orderService.find(request.getParameter("openID"));
        response.getWriter().print(JSONArray.fromObject(orders).toString());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public void createOrder(@RequestParam("openID") String openID,
                            @RequestParam("sub_orders") String subOrders,
                            @RequestParam("address_id") int address_id,
                            HttpServletResponse response){
        Result result = orderService.save(openID, subOrders, address_id);
        try {
            response.getWriter().print(JSONObject.fromObject(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
