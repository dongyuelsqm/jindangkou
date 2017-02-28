package com.kingdangkou.weixin.weixiaodan.controller.weixin;

import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.service.PageSdkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dongy on 2017-02-28.
 */
@RequestMapping("/weixin/page")
@Controller
public class PageSdkInfoController {
    @Autowired
    private PageSdkService pageSdkService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public void getPageInfo(@RequestParam("url") String url,  HttpServletResponse response) throws IOException {
        Result result = pageSdkService.getSdkInfo(url);
        response.getWriter().print(result);
    }
}
