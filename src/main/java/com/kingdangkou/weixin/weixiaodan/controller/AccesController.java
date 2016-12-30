package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.service.AccessService;
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

    @RequestMapping("/openID")
    private void getOpenID(@RequestParam("code") String code, HttpServletResponse response) throws Exception {
        Result result = accessService.getOpenID(code);
        response.getWriter().print(result);
    }
}
