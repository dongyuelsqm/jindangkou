package com.kingdangkou.weixin.weixiaodan.controller.website;

import com.kingdangkou.weixin.weixiaodan.controller.TestBase;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by dongy on 2017-01-10.
 */
public class WebSiteNotificationControllerTest extends TestBase<WebSiteNotificationController>{
    @Test
    public void add() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/website/add").param("title", "打折优惠！")).andDo(print());
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void remove() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/website/remove").param("id", "2")).andDo(print());
        resultActions.andExpect(status().isOk());
    }
}