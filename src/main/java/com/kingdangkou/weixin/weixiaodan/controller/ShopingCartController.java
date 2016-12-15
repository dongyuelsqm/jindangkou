package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.entity.TobePurchasedProductEntity;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.service.ShopingCartService;
import com.kingdangkou.weixin.weixiaodan.validator.ShopingCartValidator;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dongy on 2016-12-06.
 */
@Controller
@RequestMapping("/shopingcart")
public class ShopingCartController {
    @Autowired
    private ShopingCartValidator shopingCartValidator;

    @Autowired
    private ShopingCartService shopingCartService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(shopingCartValidator);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestParam("openID") String openID,
                    @RequestParam("product_id") String product_id,
                    @RequestParam("color") String color,
                    @RequestParam("number") String number,
                    @RequestParam("size") String size, HttpServletResponse response) throws IOException {
        Result result = shopingCartService.add(new TobePurchasedProductEntity(openID, product_id, color, number, size));
        response.getWriter().print(JSONObject.fromObject(result));
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public void del(@RequestParam("id_list") List<String> id_list, HttpServletResponse response){
        shopingCartService.del(id_list);
    }
}
