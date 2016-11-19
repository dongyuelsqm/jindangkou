package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.service.OrderDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by dongy on 2016-11-13.
 */
@Controller
@RequestMapping("/Order")
public class TakeNewOrder {
    @Autowired
    private OrderDbService orderDbService;
    @Inject
    public TakeNewOrder(OrderDbService orderDbService) {
        this.orderDbService = orderDbService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        orderDbService.get(request.getParameter("openID"));
    }
}
