package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.entity.ColorEntity;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.service.ColorService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dongy on 2016-12-16.
 */
@Controller
public class ColorController {
    @Autowired
    private ColorService colorService;
    @RequestMapping(method = RequestMethod.POST, value = "/website/color")
    public void add(@RequestParam("name") String name, HttpServletResponse response) throws IOException {
        Result result = colorService.add(name);
        response.getWriter().print(result);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/website/color")
    public void get(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<ColorEntity> colorEntities = colorService.list();
        response.getWriter().print(JSONArray.fromObject(colorEntities));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/website/color")
    public void del(@RequestParam("id") String id, HttpServletResponse response) throws IOException {
        Result result = colorService.del(id);
        response.getWriter().print(result);
    }
}
