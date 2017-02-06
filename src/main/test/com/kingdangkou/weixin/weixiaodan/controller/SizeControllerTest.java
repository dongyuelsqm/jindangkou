package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.model.Success;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by dongy on 2017-02-06.
 */
public class SizeControllerTest extends TestBase<SizeController>{
    @Test
    public void add() throws Exception {
        mockMvc.perform(post("/website/size/add").param("name", "xxl")).
                andDo(print()).
                andExpect(status().isOk()).
                andExpect(content().string(new Success().toString()));
    }

    @Test
    public void del() throws Exception {
        mockMvc.perform(post("/size/del").param("id", "2")).
                andDo(print()).
                andExpect(status().isOk()).
                andExpect(content().string(new Success().toString()));
    }

    @Test
    public void list() throws Exception {
        mockMvc.perform(get("/website/size/list")).
                andDo(print()).
                andExpect(status().isOk());
    }

}