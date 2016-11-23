package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.entity.Address;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.service.AddressService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dongy on 2016-11-20.
 */
@Controller
@RequestMapping(value = "/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(HttpServletRequest request, HttpServletResponse response){
        String openID = request.getParameter("openID");
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String district = request.getParameter("district");
        String detail = request.getParameter("detail");

        Result result = addressService.save(new Address(openID, province, city, district, detail));
        try {
            response.getWriter().print(JSONObject.fromObject(result).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void get(HttpServletRequest request, HttpServletResponse response){
        String openID = request.getParameter("openID");
        List<Address> addresses = addressService.list(openID);
        try {
            response.getWriter().print(JSONObject.fromObject(addresses));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
