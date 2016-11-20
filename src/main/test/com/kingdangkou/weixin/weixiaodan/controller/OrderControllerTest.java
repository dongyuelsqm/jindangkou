package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.entity.Order;
import com.kingdangkou.weixin.weixiaodan.model.OrderModel;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.service.OrderDbService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spitter-servlet.xml")
public class OrderControllerTest {

    private MockMvc mockMvc;
    @Mock
    private OrderDbService orderDbService;
    @InjectMocks
    private OrderController orderController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void testDoGet() throws Exception {
        ArrayList<OrderModel> orders = new ArrayList<OrderModel>();
        orders.add(new OrderModel("11", "name", 1f, 1, 1f, "address", "dddd", "finished", new Date()));
        orders.add(new OrderModel("111", "name", 1f, 1, 1f, "address", "dddd", "finished", new Date()));
        when(orderDbService.find(any(String.class))).thenReturn(orders);
        ResultActions result = mockMvc.perform(get("/order/list").param("openID", "11")).andDo(print());
        result.andExpect(status().isOk());

    }

    @Test
    public void testCreateOrder() throws Exception {
        when(orderDbService.save("11", 11, 11, 11)).thenReturn(new Result(true, "yes"));
        ResultActions resultActions = mockMvc.perform(post("/order/create")
                .param("openID", "11")
                .param("product_id", "11")
                .param("number", "11")
                .param("address_id", "11")).andDo(print());
        resultActions.andExpect(status().isOk());
    }
}