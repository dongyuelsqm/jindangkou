package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.entity.ProductEntity;
import net.sf.json.JSONArray;
import org.junit.Test;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest extends TestBase<ProductController>{

    private ProductEntity productEntity = new ProductEntity("name", "", 1.0f, "code", 10, "postal", "pictures", "videos");

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(
                get("/product/detail")
                        .param("id", "1"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testName() throws Exception {
        ArrayList<String> pictures = new ArrayList<String>();
        pictures.add("dddd");
        pictures.add("ffff");
        System.out.println(JSONArray.fromObject(pictures));

    }
}