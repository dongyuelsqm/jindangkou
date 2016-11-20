package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.model.OrderModel;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.service.OrderDbService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
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
    private OrderDbService orderDbService;
    @Inject
    public OrderController(OrderDbService orderDbService) {
        this.orderDbService = orderDbService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        List<OrderModel> orders = orderDbService.find(request.getParameter("openID"));
        response.getWriter().print(JSONArray.fromObject(orders).toString());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public void createOrder(HttpServletRequest request, HttpServletResponse response){
        String openID = request.getParameter("openID");
        int product_id = Integer.valueOf(request.getParameter("product_id"));
        int number = Integer.valueOf(request.getParameter("number"));
        int address_id = Integer.valueOf(request.getParameter("address_id"));
        Result result = orderDbService.save(openID, product_id, number, address_id);
        try {
            response.getWriter().print(JSONObject.fromObject(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
