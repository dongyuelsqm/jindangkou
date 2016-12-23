package com.kingdangkou.weixin.weixiaodan.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by dongy on 2016-12-22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spitter-servlet.xml")
public class ProductControllerTestFT {
    @Autowired
    private ProductController productController;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testList() throws Exception {
        String content = visit("/product/list").andReturn().getResponse().getContentAsString();

        JSONObject result = JSONObject.fromObject(content);
        assertEquals("true", result.getString("result"));
        JSONArray objs = JSONArray.fromObject(result.getString("objs"));
        assertEquals(1, objs.size());
        JSONObject json = (JSONObject) objs.get(0);
        assertEquals("jeans", json.getString("name"));
    }

    @Test
    public void testFind() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/product/department").param("department", "1")).andDo(print()).andExpect(status().isOk());
        String content = resultActions.andReturn().getResponse().getContentAsString();
        JSONArray jsonObject = JSONArray.fromObject(content);

        JSONObject json = (JSONObject) jsonObject.get(0);
        assertEquals("jeans", json.getString("name"));

    }

    private ResultActions visit(String url) throws Exception {
        return mockMvc.perform(get(url)).andDo(print()).andExpect(status().isOk());
    }
}
