package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.entity.Product;
import com.kingdangkou.weixin.weixiaodan.model.Success;
import com.kingdangkou.weixin.weixiaodan.service.ProductService;
import net.sf.json.JSONArray;
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

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:/spitter-servlet.xml")
public class ProductControllerTest {

    private Product product = new Product("name", 1.0f, "type", "code", 10, "postal", "pictures", "videos");

    private MockMvc mockMvc;
    @Mock
    private ProductService service;
    @InjectMocks
    ProductController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        product.setId(11111);
    }

    @Test
    public void testGet() throws Exception {
        when(service.get(any(String.class))).thenReturn(product);
        ResultActions resultActions = mockMvc.perform(
                get("/product/detail")
                        .param("id", String.valueOf(product.getId())))
                .andDo(print());
        resultActions.andExpect(status().isOk()).andExpect(content().string(contains("shirt")));
    }

    @Test
    public void testAddProduct() throws Exception {
        when(service.save(any(Product.class))).thenReturn(new Success());
        mockMvc.perform(post("/product/add").
                param("name", "product").
                param("price", "1.1").
                param("department", "shirt").
                param("size", "1").
                param("color", "b").
                param("code", "code").
                param("minimum", "10").
                param("postal", "300000").
                param("pictures", "[\"dddd\",\"ffff\"]").
                param("videos", "[\"dddd\",\"ffff\"]")).andDo(print()).andExpect(status().isOk()).andExpect(content().string("{\"detail\":\"\",\"success\":true}"));
    }

    @Test
    public void testName() throws Exception {
        ArrayList<String> pictures = new ArrayList<String>();
        pictures.add("dddd");
        pictures.add("ffff");
        System.out.println(JSONArray.fromObject(pictures));

    }
}