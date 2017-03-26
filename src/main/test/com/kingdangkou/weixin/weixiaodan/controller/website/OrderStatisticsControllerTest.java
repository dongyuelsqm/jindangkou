package com.kingdangkou.weixin.weixiaodan.controller.website;

import com.kingdangkou.weixin.weixiaodan.controller.TestBase;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by dongy on 2017-03-22.
 */
public class OrderStatisticsControllerTest extends TestBase<OrderStatisticsController>{
    @Test
    public void getOrderStatisticsByOrder() throws Exception {
        ResultActions resultActions = visit("/website/statistics/order/date", new String[]{"date", "[2017-03-12]"});
        resultActions.andExpect(status().isOk());

    }

    @Test
    public void getStatisticsByDistrict() throws Exception {
        ResultActions resultActions = visit("/website/statistics/order/district");
        resultActions.andExpect(status().isOk());

    }

    ResultActions visit(String url, String[]... params) throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = get(url);
        for (String[] param: params){
            mockHttpServletRequestBuilder.param(param[0], param[1]);
        }
        return mockMvc.perform(mockHttpServletRequestBuilder).andDo(print());
    }
}