package com.kingdangkou.weixin.weixiaodan.controller.weixin;

import com.kingdangkou.weixin.weixiaodan.controller.TestBase;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by dongy on 2017-02-28.
 */
public class PageSdkInfoControllerTest extends TestBase<PageSdkInfoController>{
    @Test
    public void getPageInfo() throws Exception {
        Thread.sleep(10*1000);
        ResultActions resultActions = mockMvc.perform(get("/weixin/page/get")).andDo(print());
        resultActions.andExpect(status().isOk());
    }

}