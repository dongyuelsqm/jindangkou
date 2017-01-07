package com.kingdangkou.weixin.weixiaodan.controller.website;

import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dongy on 2017-01-06.
 */
@RequestMapping("/website/label")
@Controller
public class WebSiteLabelController {

    @Autowired
    private LabelService labelService;
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public void add(@RequestParam("label") String label, HttpServletResponse response) throws IOException {
        Result result = labelService.add(label);
        response.getWriter().print(result);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public void get(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Result result = labelService.get();
        response.getWriter().print(result);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(@RequestParam("id") String id, HttpServletResponse response){
        labelService.delete(id);
    }
}
