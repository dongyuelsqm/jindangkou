package com.kingdangkou.weixin.weixiaodan.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by dongy on 2017-01-18.
 */
public class CollectionControllerTest extends TestBase<CollectionController>{
    @Test
    public void add() throws Exception {
        ResultActions result = mockMvc.perform(post("/collection/").
                param("openID", "1").param("product_id", "1")).andDo(print());
        result.andExpect(status().isOk());
    }

    @Test
    public void testGet() throws Exception {
        ResultActions result = mockMvc.perform(get("/collection/").
                param("openID", "1")).andDo(print());
        result.andExpect(status().isOk());
    }

    @Test
    public void del() throws Exception {
        ResultActions result = mockMvc.perform(delete("/collection/").
                param("ids", "[1]").param("openID", "1")).andDo(print());
        result.andExpect(status().isOk());
    }

}