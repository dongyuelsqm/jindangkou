package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.controller.website.WebSiteOrderController;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by dongy on 2017-01-18.
 */
public class WebSiteOrderControllerTestFT extends TestBase<WebSiteOrderController>{
    @Test
    public void ListOrdersByDate() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/website/order/date/list").
                param("begin", "2016-01-01 00:00:00").param("end", "2017-01-01 00:00:00")).andDo(print());
        resultActions.andExpect(status().isOk());

    }
}
