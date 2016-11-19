package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.entity.Address;
import com.kingdangkou.weixin.weixiaodan.entity.Customer;
import com.kingdangkou.weixin.weixiaodan.entity.Product;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dongy on 2016-11-16.
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

    private final int SUCCESS = 0;
    @Autowired
    private RegisterService service;
    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public void registerCustomer(HttpServletRequest request, HttpServletResponse response){
        String openID = request.getParameter("openID");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");
        Result result = service.save(new Customer(openID, name, gender, phone));
        getResponse(response, SUCCESS);
    }

    private void getResponse(HttpServletResponse response, int result){
        try {
            response.getWriter().print(result);
        } catch (IOException e) {
            return;
        }
    }

    @RequestMapping(value = "/address", method = RequestMethod.POST)
    public void registerAddress(HttpServletRequest request, HttpServletResponse response){
        String openID = request.getParameter("openID");
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String district = request.getParameter("district");
        String detail = request.getParameter("detail");
        service.save(new Address(openID, province, city, district, detail));
        getResponse(response, SUCCESS);
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public void registerProduct(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        int department = Integer.valueOf(request.getParameter("department"));
        float unit_price = Float.valueOf(request.getParameter("price"));
        String description = request.getParameter("description");
        service.save(new Product(name, department, unit_price, description));
        getResponse(response, SUCCESS);
    }

    public RegisterService getService() {
        return service;
    }

    public void setService(RegisterService service) {
        this.service = service;
    }
}
