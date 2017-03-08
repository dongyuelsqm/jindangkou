package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dongy on 2016-11-30.
 */
@RequestMapping("/website")
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(@RequestParam("username") String username,
                      @RequestParam("password") String password, HttpServletResponse response) throws IOException {
        boolean result = loginService.check(username, password);
        response.getWriter().print(result);
    }
}
