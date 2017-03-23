package com.kingdangkou.weixin.weixiaodan.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by dongy on 2017-03-08.
 */
public class LoginControllerTest extends TestBase<LoginController> {
    @Test
    public void login() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/website/login").param("username", "username").param("password", "password")).andDo(print());
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void udpate() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/website/code/update").
                param("id", "1").
                param("old_code", "new_code").
                param("new_code", "new_code").
                param("confirm_code", "new_code")).andDo(print());
        resultActions.andExpect(status().isOk());

    }

    @Test
    public void udpate_withInvalidCode() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/website/code/update").
                param("id", "1").
                param("old_code", "code").
                param("new_code", "new_code").
                param("confirm_code", "new_code")).andDo(print());
        resultActions.andExpect(status().isOk());

    }
}