package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.service.ShopingCartService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dongy on 2016-12-06.
 */
@Controller
@RequestMapping("/shopingcart")
public class ShopingCartController {

    @Autowired
    private ShopingCartService shopingCartService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestParam("openID") String openID,
                    @RequestParam("product_id") String product_id,
                    @RequestParam("color_id") String color,
                    @RequestParam("number") int number,
                    @RequestParam("size_id") String size,
                    HttpServletResponse response) throws IOException {
        Result result = shopingCartService.add(openID, product_id, color, size, number);
        response.getWriter().print(JSONObject.fromObject(result));
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public void del(@RequestParam("ids") String ids, @RequestParam("open_id") String openId, HttpServletResponse response){
        shopingCartService.del(ids, openId);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void get(@RequestParam("open_id") String openId, HttpServletResponse response) throws IOException {
        Result result = shopingCartService.list(openId);
        response.getWriter().print(result);
    }


}
