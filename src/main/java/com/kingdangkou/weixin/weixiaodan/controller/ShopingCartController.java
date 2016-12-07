package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.validator.ShopingCartValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

/**
 * Created by dongy on 2016-12-06.
 */
@Controller
@RequestMapping("/shopingcart")
public class ShopingCartController {
    @Autowired
    private ShopingCartValidator shopingCartValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(shopingCartValidator);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestParam("openID") String openID,
                    @RequestParam("product_id") @Valid String product_id,
                    @RequestParam("color") String color,
                    @RequestParam("number") String number,
                    @RequestParam("size") String size, HttpServletResponse response) throws IOException {
        //shopingCartValidator.validate(openID, errors);
        response.getWriter().print("{\"detail\":\"success\",\"success\":true}");
    }
}
