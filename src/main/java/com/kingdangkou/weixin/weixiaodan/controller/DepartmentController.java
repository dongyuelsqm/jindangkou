package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.model.ListResult;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dongy on 2016-12-22.
 */
@RequestMapping("/department")
@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(method = RequestMethod.POST)
    public void add(@RequestParam("name") String name, HttpServletResponse response) throws IOException {
        Result result = departmentService.add(name);
        response.getWriter().print(result);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void del(@RequestParam("id") String id, HttpServletResponse response) throws IOException {
        Result result = departmentService.delete(id);
        response.getWriter().print(result);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public void list(HttpServletResponse response) throws IOException {
        ListResult result = departmentService.list();
        response.getWriter().print(result);
    }

}
