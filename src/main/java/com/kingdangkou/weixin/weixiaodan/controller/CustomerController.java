package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.entity.Customer;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.service.CustomerService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dongy on 2016-11-20.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public void get(HttpServletRequest request, HttpServletResponse response){
        Customer customer = customerService.get(request);
        getResponse(response, JSONObject.fromObject(customer).toString());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void registerCustomer(HttpServletRequest request, HttpServletResponse response){
        String openID = request.getParameter("openID");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");
        Result result = customerService.save(new Customer(openID, name, gender, phone));
        getResponse(response, JSONObject.fromObject(result).toString());
    }

    private void getResponse(HttpServletResponse response, String result){
        try {
            response.getWriter().print(result);
        } catch (IOException e) {
            return;
        }
    }
}
