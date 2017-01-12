package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.service.AccessService;
import com.kingdangkou.weixin.weixiaodan.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by dongy on 2016-12-28.
 */
@RequestMapping("/access")
public class AccesController {
    @Autowired
    private AccessService accessService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/openID")
    private void getOpenID(@RequestParam("code") String code, HttpServletResponse response) throws Exception {
        Result result = accessService.getOpenID(code);
        response.getWriter().print(result);
    }

    private void getCustomerInfo(@RequestParam("code") String code, HttpServletResponse response) throws Exception {
        Result result = accessService.getOpenID(code);
        Result customer = customerService.get((String) result.getDetail());
        response.getWriter().print(customer);
    }


}
