package com.kingdangkou.weixin.weixiaodan.controller.weixin;

import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dongy on 2017-01-10.
 */
@Controller
@RequestMapping("/notification/")
public class WeixinNotificationController {
    @Autowired
    private NotificationService service;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Result result = service.list();
        response.getWriter().print(result);
    }

    @RequestMapping(value = "latest", method = RequestMethod.GET)
    public void get(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Result result = service.getLatestNotification();
        response.getWriter().print(result);
    }
}
