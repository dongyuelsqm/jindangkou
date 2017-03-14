package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.entity.Address;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AddressControllerTest extends TestBase<AddressController>{

    private Address address = new Address("zhangsan", "454545", "111", "zhejiang", "hangzhou", "xihu", "detail");


    @Test
    public void testRegister() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                post("/address/register")
                        .param("name", address.getName())
                        .param("phone", address.getMobile())
                        .param("openID", address.getOpenID())
                        .param("province", address.getProvinceName())
                        .param("city", address.getCityName())
                        .param("district", address.getExpAreaName())
                        .param("detail", address.getAddress()))
                .andDo(print());
        resultActions.andExpect(status().isOk()).andExpect(content().string("{\"detail\":\"\",\"success\":true}"));
    }

    @Test
    public void testGetFromDB() throws Exception {
        ResultActions result = mockMvc.perform(get("/address/list").param("openID", "ssss")).andDo(print());
        result.andExpect(status().isOk());
    }
}