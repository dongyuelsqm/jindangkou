package com.kingdangkou.weixin.weixiaodan.controller.website;

import com.kingdangkou.weixin.weixiaodan.controller.TestBase;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by dongy on 2017-03-22.
 */
public class OrderStatisticsControllerTest extends TestBase<OrderStatisticsController>{
    @Test
    public void getOrderStatisticsByOrder() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/website/statistics/order/date").param("date", "[2017-03-12]")).andDo(print());
        resultActions.andExpect(status().isOk());

    }

}