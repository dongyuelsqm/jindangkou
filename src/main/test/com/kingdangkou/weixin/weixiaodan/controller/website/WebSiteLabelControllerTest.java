package com.kingdangkou.weixin.weixiaodan.controller.website;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
/**
 * Created by dongy on 2017-01-06.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spitter-servlet.xml")
public class WebSiteLabelControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebSiteLabelController labelController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(labelController).build();
    }

    @Test
    public void When_got_from_db_Then_hot_label_Got() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/website/label/get").param("title", "hot")).andDo(print());
        resultActions.andExpect(status().isOk());
    }
    @Test
    public void add() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/website/label/add").param("title", "hot")).andDo(print());
        resultActions.andExpect(status().isOk()).andExpect(content().string("{\"detail\":\"\",\"success\":true}"));


    }

}