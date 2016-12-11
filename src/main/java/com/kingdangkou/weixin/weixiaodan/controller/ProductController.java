package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.entity.Product;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.service.ProductService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    public void get(@RequestParam("id") String id, HttpServletResponse response) throws IOException, FileUploadException {
        Product product = productService.get(id);
        response.getWriter().print(JSONObject.fromObject(product).toString());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().print(JSONArray.fromObject(productService.list()));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public void register(@RequestParam("name") String name,
                         @RequestParam("price") String price,
                         @RequestParam("department") String department,
                         @RequestParam("size") String size,
                         @RequestParam("color") String colors,
                         @RequestParam("code") String code,
                         @RequestParam("minimum") String minimum,
                         @RequestParam("postal") String postal,
                         @RequestParam("pictures") String pictures,
                         @RequestParam("videos") String videos,
                         HttpServletResponse response) throws IOException {
        Result result = productService.save(new Product(name, Float.valueOf(price), department, size, colors, code, Integer.valueOf(minimum), postal, pictures, videos));
        response.getWriter().print(JSONObject.fromObject(result));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public void save(@RequestParam("id") String id, @RequestParam("name") String name,
                     @RequestParam("value") String value){
        productService.update(id, name, value);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/remove")
    public void remove(@RequestParam("id") String id){

    }
}
