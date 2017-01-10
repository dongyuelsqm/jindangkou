package com.kingdangkou.weixin.weixiaodan.controller.website;

import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dongy on 2017-01-10.
 */
@Controller
@RequestMapping("/website")
public class WebSiteNotificationController {
    @Autowired
    private NotificationService notificationService;
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public void add(@RequestParam("title") String title, HttpServletResponse response) throws IOException {
        Result result = notificationService.save(title);
        response.getWriter().print(result);
    }
}
