package com.kingdangkou.weixin.weixiaodan.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by dongy on 2016-12-22.
 */
public class ProductControllerTestFT extends TestBase<ProductController>{
    @Test
    public void testList() throws Exception {
        String content = visit("/product/list").andReturn().getResponse().getContentAsString();

        JSONObject result = JSONObject.fromObject(content);
        assertEquals("true", result.getString("success"));
        JSONArray objs = JSONArray.fromObject(result.getString("detail"));
        JSONObject json = (JSONObject) objs.get(0);
        assertEquals("jeans", json.getString("name"));
    }

    @Test
    public void testFind() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/product/department").param("department", "1")).andDo(print()).andExpect(status().isOk());
        String content = resultActions.andReturn().getResponse().getContentAsString();
        JSONObject jsonObject = JSONObject.fromObject(content);

        JSONObject json = (JSONObject) JSONArray.fromObject(((JSONObject) jsonObject).getString("detail")).get(0);
        assertEquals("jeans", json.getString("name"));

    }

    @Test
    public void test_find_by_labels() throws Exception {
        ResultActions result = mockMvc.perform(get("/product/label").param("label_id", "1")).andDo(print());
        MvcResult mvcResult = result.andExpect(status().isOk()).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        assertTrue(contentAsString.contains("label"));
    }

    @Test
    public void test_get_by_id() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/product/label").param("label_id", "1")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse();


    }

    private ResultActions visit(String url) throws Exception {
        return mockMvc.perform(get(url)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void search() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/product/search").param("name", "jeans")).andDo(print());
        resultActions.andExpect(status().isOk());
    }
}
