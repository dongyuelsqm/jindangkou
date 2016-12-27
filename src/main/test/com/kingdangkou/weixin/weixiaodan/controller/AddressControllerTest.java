package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.entity.Address;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.service.AddressService;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spitter-servlet.xml")
public class AddressControllerTest {

    private Address address = new Address("zhangsan", "454545", "111", "zhejiang", "hangzhou", "xihu", "detail");
    private MockMvc mockMvc;
    @Mock
    private AddressService addressService;
    @InjectMocks
    private AddressController addressController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(addressController).build();
    }

    @Test
    public void testRegister() throws Exception {
        when(addressService.save(any(Address.class))).thenReturn(new Result(true, "success"));
        ResultActions resultActions = mockMvc.perform(
                post("/address/register")
                        .param("name", address.getName())
                        .param("phone", address.getPhone())
                        .param("openID", address.getOpenID())
                        .param("province", address.getProvince())
                        .param("city", address.getCity())
                        .param("district", address.getDisctrict())
                        .param("detail", address.getDetail()))
                .andDo(print());
        resultActions.andExpect(status().isOk()).andExpect(content().string("{\"detail\":\"success\",\"success\":true}"));
    }

    @Test
    public void testGet() throws Exception {
        ArrayList<Address> addresses = new ArrayList<Address>();
        addresses.add(new Address("zhangsan", "454545", "111", "zhejiang", "hangzhou", "xihu", "detail"));
        addresses.add(new Address("zhangsan", "454545", "112", "zhejiang", "hangzhou", "gongshu", "detail"));
        when(addressService.list(any(String.class))).thenReturn(addresses);
        ResultActions result = mockMvc.perform(get("/address/list").param("openID", "111")).andDo(print());
        result.andExpect(status().isOk()).andExpect(content().string(contains("shirt")));

    }

    @Test
    public void testGetFromDB() throws Exception {
        ResultActions result = mockMvc.perform(get("/address/list").param("openID", "111")).andDo(print());
        result.andExpect(status().isOk()).andExpect(content().string(contains("city")));

    }
}