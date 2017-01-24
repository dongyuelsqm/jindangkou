package com.kingdangkou.weixin.weixiaodan.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by dongy on 2016-12-23.
 */
public class AddressControllerTestFT extends TestBase<AddressController> {

    @Test
    public void update() throws Exception {
        ResultActions result = mockMvc.perform(post("/address/update").
                param("id", "1").param("name", "name").param("phone", "phone").
                param("openID", "openID").param("province", "province").param("city", "city").
                param("district", "district").param("detail", "detail")).andDo(print());
        result.andExpect(status().isOk());
    }

    @Test
    public void testGetFromDB() throws Exception {
        ResultActions result = mockMvc.perform(get("/address/list").param("openID", "ssss")).andDo(print());
        result.andExpect(status().isOk());

    }

    @Test
    public void testAddAndDel() throws Exception {
        ResultActions result = mockMvc.perform(post("/address/register").
                param("name", "zhangsan").
                param("phone", "151111111").
                param("openID", "ssss").
                param("province", "zhejiang").
                param("city", "hangzhou").
                param("district", "xihu").
                param("detail", "zheda")).andDo(print());
        result.andExpect(status().isOk()).andExpect(content().string("{\"detail\":\"\",\"success\":true}"));
    }

    @Test
    public void testDel() throws Exception {
        ResultActions result = mockMvc.perform(delete("/address").param("id", "2")).andDo(print());
        result.andExpect(status().isOk()).andExpect(content().string("{\"detail\":\"\",\"success\":true}"));
    }
}
