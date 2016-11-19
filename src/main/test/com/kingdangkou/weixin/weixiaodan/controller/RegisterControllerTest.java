package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.entity.Address;
import com.kingdangkou.weixin.weixiaodan.entity.Customer;
import com.kingdangkou.weixin.weixiaodan.entity.Product;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.service.RegisterService;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spitter-servlet.xml")
public class RegisterControllerTest {

    private Customer cutomer = new Customer("11", "lisi", "nan", "159999");
    private Product product = new Product("shirt", 11, 2f, "desc", "pic");
    private Address address = new Address("11", "zhejiang", "hangzhou", "xihu", "road");

    private MockMvc mockMvc;
    @Mock
    private RegisterService service;
    @InjectMocks
    RegisterController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testRegisterCustomer() throws Exception {
        when(service.save(any(Customer.class))).thenReturn(new Result(true, "success"));
        ResultActions resultActions = mockMvc.perform(
                post("/register/customer")
                        .param("openID", cutomer.getId())
                        .param("name", cutomer.getName())
                        .param("gender", cutomer.getGender())
                        .param("phone", cutomer.getPhone()))
                .andDo(print());
        resultActions.andExpect(status().isOk()).andExpect(content().string("0"));
        verify(service).save(cutomer);
    }

    @Test
    public void testRegisterAddress() throws Exception {
        when(service.save(any(Address.class))).thenReturn(new Result(true, "success"));
        ResultActions resultActions = mockMvc.perform(
                post("/register/address")
                        .param("openID", address.getOpenID())
                        .param("province", address.getProvince())
                        .param("city", address.getCity())
                        .param("district", address.getDisctrict())
                        .param("detail", address.getDetail()))
                .andDo(print());
        resultActions.andExpect(status().isOk()).andExpect(content().string("0"));
    }

    @Test
    public void testRegisterProduct() throws Exception {
        when(service.save(any(Product.class))).thenReturn(new Result(true, "success"));
        ResultActions resultActions = mockMvc.perform(
                post("/register/product")
                        .param("id", String.valueOf(product.getId()))
                        .param("name", product.getName())
                        .param("department", String.valueOf(product.getDepartment()))
                        .param("price", String.valueOf(product.getPrice()))
                        .param("description", product.getDescription()))
                .andDo(print());
        resultActions.andExpect(status().isOk()).andExpect(content().string("0"));
    }
}