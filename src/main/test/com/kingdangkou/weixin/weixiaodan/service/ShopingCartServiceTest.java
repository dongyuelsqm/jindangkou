package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.controller.ShopingCartController;
import com.kingdangkou.weixin.weixiaodan.controller.TestBase;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ShopingCartServiceTest extends TestBase<ShopingCartController>{
    @Test
    public void testAdd() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/shopingcart/add").
                param("openID", "1").param("product_id", "1").param("color_id", "1").param("size_id", "1").param("number", "1")).andDo(print());
        resultActions.andExpect(status().isOk());

    }

    @Test
    public void list() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/shopingcart/list/").param("open_id", "1")).andDo(print());
        resultActions.andExpect(status().isOk());

    }

    @Test
    public void del() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/shopingcart/del").
                param("open_id", "1").param("ids", "[1,2, 3]")).andDo(print());
        resultActions.andExpect(status().isOk());
    }
}