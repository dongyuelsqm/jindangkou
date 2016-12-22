package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.entity.Product;
import com.kingdangkou.weixin.weixiaodan.model.ListResult;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.service.ProductService;
import com.kingdangkou.weixin.weixiaodan.utils.configs.ProductJsonConfig;
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
import java.util.List;

/**
 * Created by dongy on 2016-11-19.
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductJsonConfig productJsonConfig;

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET, value = "/detail")
    public void get(@RequestParam("id") String id, HttpServletResponse response) throws IOException, FileUploadException {
        Product result = productService.get(id);
        response.getWriter().print(result);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ListResult result = productService.list();
        response.getWriter().print(JSONObject.fromObject(result, productJsonConfig));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/department")
    public void find(@RequestParam("department") String department, HttpServletResponse response) throws IOException {
        List<Product> products = productService.list(department);
        response.getWriter().print(JSONArray.fromObject(products));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public void register(@RequestParam("name") String name,
                         @RequestParam("department") String department,
                         @RequestParam("descriptive") String descriptive,
                         @RequestParam("price") float price,
                         @RequestParam("quantity") String quantity,
                         @RequestParam("code") String code,
                         @RequestParam("minimum") int minimum,
                         @RequestParam("postal") String postal,
                         @RequestParam("pictures") String pictures,
                         @RequestParam("videos") String videos,
                         HttpServletResponse response) throws IOException {
        Result result = productService.save(name, descriptive, price, department, code, minimum, postal, pictures, videos, quantity);
        returnOperationResult(response, result);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public void save(@RequestParam("id") String id, @RequestParam("name") String name,
                     @RequestParam("value") String value, HttpServletResponse response) throws IOException {
        Result result = productService.update(id, name, value);
        returnOperationResult(response, result);
    }

    @RequestMapping(method = RequestMethod.GET, value = "check")
    public void getNumber(@RequestParam("id") String id,
                          @RequestParam("color") String color,
                          @RequestParam("size") String size,
                          HttpServletResponse response) throws IOException {
        Result result = productService.getNumber(id, color, size);
        returnOperationResult(response, result);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/number/update")
    public void updateNumber(@RequestParam("id") String id,
                             @RequestParam("color") String color,
                             @RequestParam("size") String size,
                             @RequestParam("number") int number, HttpServletResponse response) throws IOException {
        Result result = productService.updateNumber(id, color, size, number);
        returnOperationResult(response, result);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/remove")
    public void remove(@RequestParam("id") String id, HttpServletResponse response) throws IOException {
        Result result = productService.remove(id);
        returnOperationResult(response, result);
    }

    private void returnOperationResult(HttpServletResponse response, Result result) throws IOException {
        response.getWriter().print(result);
    }
}
