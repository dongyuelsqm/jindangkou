package com.kingdangkou.weixin.weixiaodan.controller.weixin;

import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dongy on 2017-01-06.
 */
@RequestMapping("/weixin/label")
@Controller
public class LabelController {

    @Autowired
    private LabelService labelService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public void get(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Result result = labelService.get();
        response.getWriter().print(result);
    }
}
