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
        response.getWriter().print(JSONObject.fromObject(result, productJsonConfig));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ListResult result = productService.list();
        String callback = request.getParameter("callback");
        response.getWriter().print("callback(" + JSONObject.fromObject(result, productJsonConfig) + ")");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/department")
    public void find(@RequestParam("department") String department, HttpServletResponse response) throws IOException {
        List<Product> products = productService.list(department);
        response.getWriter().print(JSONArray.fromObject(products, productJsonConfig));
    }

    @RequestMapping(method = RequestMethod.GET, value = "check")
    public void getNumber(@RequestParam("id") String id,
                          @RequestParam("color") String color,
                          @RequestParam("size") String size,
                          HttpServletResponse response) throws IOException {
        Result result = productService.getNumber(id, color, size);
        returnOperationResult(response, result);
    }

    private void returnOperationResult(HttpServletResponse response, Result result) throws IOException {
        response.getWriter().print(result);
    }
}
