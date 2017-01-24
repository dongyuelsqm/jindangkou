package com.kingdangkou.weixin.weixiaodan.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Created by dongy on 2016-12-06.
 */
public class ShopingCartControllerTest extends TestBase<ShopingCartController>{
    private final String EXPECT_CONTENT_SUCCESS = "{\"detail\":\"\",\"success\":true}";
    private final String EXPECT_CONTENT_FAIL = "{\"detail\":\"\",\"success\":fail}";

    @Test
    public void addProducts() throws Exception {
        ResultActions result = mockMvc.perform(post("/shopingcart/add").
                param("openID", "11").
                param("product_id", "1").
                param("number", "1").
                param("color_id", "1").
                param("size_id", "1")).
                andDo(print());
        result.andExpect(status().isOk()).andExpect(content().string(EXPECT_CONTENT_SUCCESS));
    }

    @Test
    public void list() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/shopingcart/list").param("open_id", "1")).andDo(print());
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void addProductsWithInvalidParameter() throws Exception {
        ResultActions result = mockMvc.perform(post("/shopingcart/add").
                param("openID", "11").
                param("product_id", "***").
                param("number", "1").
                param("color_id", "1").
                param("size_id", "2")).
                andDo(print());
        result.andExpect(status().isOk()).andExpect(content().string(EXPECT_CONTENT_FAIL));
    }
}
