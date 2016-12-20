package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by dongy on 2016-12-17.
 */
@RequestMapping("/websites/size")
public class SizeController {
    @Autowired
    private SizeService sizeService;
    @RequestMapping(method = RequestMethod.GET)
    public void add(@RequestParam("name") String name, HttpServletResponse response){

    }
}
