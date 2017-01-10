package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.entity.ProductEntity;
import com.kingdangkou.weixin.weixiaodan.model.ProductModel;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.model.Success;
import com.kingdangkou.weixin.weixiaodan.service.ProductService;
import net.sf.json.JSONArray;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest extends TestBase<ProductController>{

    private ProductEntity productEntity = new ProductEntity("name", "", 1.0f, "code", 10, "postal", "pictures", "videos");

    @Mock
    private ProductService service;
    @InjectMocks
    ProductController controller;

    @Test
    public void testGet() throws Exception {
        when(service.get(any(String.class))).thenReturn(new Result(true, new ProductModel(productEntity, 1)));
        mockMvc.perform(
                get("/product/detail")
                        .param("id", String.valueOf(productEntity.getId())))
                .andDo(print()).andExpect(status().isOk()).andExpect(content().string(contains("shirt")));
    }

    @Test
    public void testAddProduct() throws Exception {
        when(service.save(any(ProductEntity.class))).thenReturn(new Success());
        mockMvc.perform(post("website/product/add").
                param("name", "product").
                param("price", "1.1").
                param("quantity", "{quantity:\"1\", size:\"1\", color:\"1\", }").
                param("department", "shirt").
                param("code", "code").
                param("minimum", "10").
                param("postal", "300000").
                param("pictures", "[\"dddd\",\"ffff\"]").
                param("videos", "[\"dddd\",\"ffff\"]").
                param("descriptive", "good")).andDo(print()).andExpect(status().isOk()).andExpect(content().string("{\"detail\":\"\",\"success\":true}"));
    }

    @Test
    public void testName() throws Exception {
        ArrayList<String> pictures = new ArrayList<String>();
        pictures.add("dddd");
        pictures.add("ffff");
        System.out.println(JSONArray.fromObject(pictures));

    }
}