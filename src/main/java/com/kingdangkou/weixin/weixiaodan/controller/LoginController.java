package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.entity.User;
import com.kingdangkou.weixin.weixiaodan.model.Failure;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.model.Success;
import com.kingdangkou.weixin.weixiaodan.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
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
    public void login(HttpServletRequest request, @RequestParam("username") String username,
                      @RequestParam("password") String password, HttpServletResponse response) throws IOException {
        User user = loginService.check(username, password);
        if (user != null){
            request.getSession().setAttribute("_USER_INFO_LOGIN_NAME_", username);
            request.getSession().setAttribute("_USER_INFO_USER_ID_", user.getId());
            request.getSession().setAttribute("_USER_INFO_USER_INFO_", user);
            response.getWriter().print(new Success());
        }else {
            response.getWriter().print(new Failure("not match"));
        }
    }
    @RequestMapping(value = "/code/update", method = RequestMethod.POST)
    public void update(@RequestParam("id") String id,
                       @RequestParam("old_code") String oldCode,
                       @RequestParam("new_code") String newCode,
                       @RequestParam("confirm_code") String confirmCode, HttpServletResponse response) throws IOException {
        Result result = loginService.update(id, oldCode, newCode, confirmCode);
        response.getWriter().print(result);
    }


}
