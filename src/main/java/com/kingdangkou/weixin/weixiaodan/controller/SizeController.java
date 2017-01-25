package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dongy on 2016-12-20.
 */
@Controller
@RequestMapping("/size")
public class SizeController {
    @Autowired
    private SizeService sizeService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestParam("name") String name, HttpServletResponse response) throws IOException {
        Result result = sizeService.add(name);
        response.getWriter().print(result);
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public void del(@RequestParam("id") int id, HttpServletResponse response) throws IOException {
        Result result = sizeService.del(String.valueOf(id));
        response.getWriter().print(result);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Result result = sizeService.list();
        response.getWriter().print(result);
    }

}
