package com.kingdangkou.weixin.weixiaodan.controller.weixin;

import com.kingdangkou.weixin.weixiaodan.controller.TestBase;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Created by dongy on 2017-01-10.
 */
public class WeixinNotificationControllerTest extends TestBase<WeixinNotificationController> {
    @Test
    public void list() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/notification/list")).andDo(print());
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void getLatest() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/notification/latest")).andDo(print());
        resultActions.andExpect(status().isOk());
    }
}