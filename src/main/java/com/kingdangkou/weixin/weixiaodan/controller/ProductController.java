package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.entity.Product;
import com.kingdangkou.weixin.weixiaodan.service.ProductService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dongy on 2016-11-19.
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @RequestMapping(method = RequestMethod.GET, value = "/detail")
    public void get(HttpServletRequest request, HttpServletResponse response){
        Product product = productService.get(request.getParameter("id"));
        try {
            response.getWriter().print(JSONObject.valueToString(product).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
