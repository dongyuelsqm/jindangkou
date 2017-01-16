package com.kingdangkou.weixin.weixiaodan.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerTestFT extends TestBase<OrderController> {

    @Test
    public void testDoGet() throws Exception {
        ResultActions result = mockMvc.perform(get("/order/list").param("openID", "1")).andDo(print());
        result.andExpect(status().isOk());

    }

    @Test
    public void testCreateOrder() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/order/add").
                param("openID", "1").
                param("sub_orders", "[{number:1, color:2, size:1, product_id:1}]").
                param("address_id", "1")).andDo(print());
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testFindSingleState() throws Exception {

    }

    @Test
    public void testListOrders() throws Exception {

    }
}