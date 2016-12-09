package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.entity.TobePurchasedProductEntity;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.service.ShopingCartService;
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

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by dongy on 2016-12-06.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spitter-servlet.xml")
public class ShopingCartControllerTest {
    private final String EXPECT_CONTENT_SUCCESS = "{\"detail\":\"success\",\"success\":true}";
    private final String EXPECT_CONTENT_FAIL = "{\"detail\":\"success\",\"success\":fail}";
    private MockMvc mockMvc;
    @InjectMocks
    private ShopingCartController controller;

    @Mock
    private ShopingCartService service;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void addProducts() throws Exception {
        when(service.add(any(TobePurchasedProductEntity.class))).thenReturn(new Result(true, "success"));
        ResultActions result = mockMvc.perform(post("/shopingcart/add").
                param("openID", "11").
                param("product_id", "1").
                param("number", "1").
                param("color", "blue").
                param("size", "mx")).
                andDo(print());
        result.andExpect(status().isOk()).andExpect(content().string(EXPECT_CONTENT_SUCCESS));
    }

    @Test
    public void addProductsWithInvalidParameter() throws Exception {
        when(service.add(any(TobePurchasedProductEntity.class))).thenReturn(new Result(true, "success"));
        ResultActions result = mockMvc.perform(post("/shopingcart/add").
                param("openID", "11").
                param("product_id", "***").
                param("number", "1").
                param("color", "blue").
                param("size", "mx")).
                andDo(print());
        result.andExpect(status().isOk()).andExpect(content().string(EXPECT_CONTENT_FAIL));
    }
}
