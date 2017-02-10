package com.kingdangkou.weixin.weixiaodan.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by dongy on 2017-02-10.
 */
public class AccesControllerTest extends TestBase<AccesController> {

    @Test
    public void getOpenID() throws Exception {
        ResultActions result = mockMvc.perform(get("/access/openID").param("code", "0310TRia0SsgBu15vxka0YsCia00TRiP")).andDo(print());
        result.andExpect(status().isOk());
    }

    @Test
    public void getCustomerInfo() throws Exception {
        ResultActions result = mockMvc.perform(get("/access/customer_info").param("code", "0310TRia0SsgBu15vxka0YsCia00TRiP")).andDo(print());
        result.andExpect(status().isOk());
    }

}