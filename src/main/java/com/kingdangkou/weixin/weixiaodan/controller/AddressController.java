package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.entity.Address;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.service.AddressService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void list(@RequestParam("openID") String openID, HttpServletResponse response) throws IOException {
        List<Address> addresses = addressService.list(openID);
        response.getWriter().print(JSONArray.fromObject(addresses));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@RequestParam("name") String name,
                         @RequestParam("phone") String phone,
                         @RequestParam("openID") String openID,
                         @RequestParam("province") String province,
                         @RequestParam("city") String city,
                         @RequestParam("district") String district,
                         @RequestParam("detail") String detail,
                         HttpServletResponse response) throws IOException {
        Result result = addressService.save(new Address(name, phone, openID, province, city, district, detail));
        response.getWriter().print(JSONObject.fromObject(result).toString());
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestParam("name") String name,
                         @RequestParam("phone") String phone,
                         @RequestParam("openID") String openID,
                         @RequestParam("province") String province,
                         @RequestParam("city") String city,
                         @RequestParam("district") String district,
                         @RequestParam("detail") String detail,
                         HttpServletResponse response) throws IOException {

        Result result = addressService.update(new Address(name, phone, openID, province, city, district, detail));
        response.getWriter().print(JSONObject.fromObject(result).toString());
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(@RequestParam("id") String id, HttpServletResponse response) throws IOException {
        Result result = addressService.del(id);
        response.getWriter().print(result);

    }
}
