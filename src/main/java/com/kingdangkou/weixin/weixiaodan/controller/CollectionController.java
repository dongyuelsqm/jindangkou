package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.entity.CollectionEntity;
import com.kingdangkou.weixin.weixiaodan.entity.Product;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.service.CollectionService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dongy on 2016-12-07.
 */
@Controller
@RequestMapping("/collection")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    @RequestMapping(method = RequestMethod.POST)
    public void add(@RequestParam("openID") String openID,
                    @RequestParam("product_id") String product_id, HttpServletResponse response) throws IOException {
        Result result = collectionService.add(new CollectionEntity(openID, product_id));
        response.getWriter().print(JSONObject.fromObject(result));
    }

    @RequestMapping(method = RequestMethod.GET)
    public void get(@RequestParam("openID") String openID, HttpServletResponse response) throws IOException {
        List<Product> products = collectionService.get(openID);
        response.getWriter().print(JSONObject.fromObject(products));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void del(@RequestParam("id") String id, HttpServletResponse response) throws IOException {
        Result result = collectionService.del(id);
        response.getWriter().print(JSONObject.fromObject(result));
    }

}
