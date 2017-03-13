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
        ResultActions result = mockMvc.perform(get("/access/openID").param("code", "041ba4e510xn4P1HqGd51bFYd51ba4ec")).andDo(print());
        result.andExpect(status().isOk());
    }

    @Test
    public void getCustomerInfo() throws Exception {
        ResultActions result = mockMvc.perform(get("/access/customer_info").param("code", "031hEFsJ0iUU342TrKrJ0XYzsJ0hEFs0")).andDo(print());
        result.andExpect(status().isOk());
    }

}