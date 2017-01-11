package com.kingdangkou.weixin.weixiaodan.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by dongy on 2017-01-11.
 */
public class WebSiteProductControllerTest extends TestBase<WebSiteProductController> {
    @Test
    public void add() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/website/product/add").
                param("name", "product").
                param("department", "1").
                param("descriptive", "good").
                param("price", "1.1").
                param("quantity", "[{quantity:\"1\", size:\"1\", color:\"1\"}]").
                param("code", "code").
                param("minimum", "10").
                param("postal", "300000").
                param("pictures", "[\"dddd.gif\",\"ffff.gif\"]").
                param("videos", "[\"dddd.mp4\",\"ffff.mp4\"]").
                param("label", "[\"1\"]")).andDo(print());
        resultActions.andExpect(status().isOk());
    }

}