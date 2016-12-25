package com.kingdangkou.weixin.weixiaodan.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by dongy on 2016-12-23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spitter-servlet.xml")
public class AddressControllerTestFT {
    @Autowired
    private AddressController addressController;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(addressController).build();
    }

    @Test
    public void testGetFromDB() throws Exception {
        ResultActions result = mockMvc.perform(get("/address/list").param("openID", "111")).andDo(print());
        result.andExpect(status().isOk()).andExpect(content().string("[{\"city\":\"hangzhou\",\"detail\":\"\",\"disctrict\":\"\",\"id\":1,\"name\":\"\",\"openID\":\"111\",\"phone\":\"\",\"province\":\"zhejiang\"},{\"city\":\"hangzhou\",\"detail\":\"\",\"disctrict\":\"\",\"id\":2,\"name\":\"\",\"openID\":\"111\",\"phone\":\"\",\"province\":\"zhejiang\"}]"));

    }

    @Test
    public void testAddAndDel() throws Exception {
        ResultActions result = mockMvc.perform(post("/address/register").
                param("name", "zhangsan").
                param("phone", "151111111").
                param("openID", "ssss").
                param("province", "zhejiang").
                param("city", "hangzhou").
                param("district", "xihu").
                param("detail", "zheda")).andDo(print());
        result.andExpect(status().isOk()).andExpect(content().string("{\"detail\":\"\",\"success\":true}"));
    }

    @Test
    public void testDel() throws Exception {
        ResultActions result = mockMvc.perform(delete("/address").param("id", "3")).andDo(print());
        result.andExpect(status().isOk()).andExpect(content().string("{\"detail\":\"\",\"success\":true}"));


    }
}
