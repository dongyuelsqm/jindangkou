package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.entity.Customer;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spitter-servlet.xml")
public class RegisterControllerTest {

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
        Customer cutomer = new Customer("11", "lisi", "nan", "159999");
        mockMvc.perform(post("/register/customer").param("openID", "11").param("name", "lisi")
                .param("gender", "nan").param("phone", "111")).andDo(print()).andExpect(status().isOk());
        verify(service).save(cutomer);


    }

    @Test
    public void testRegisterAddress() throws Exception {

    }

    @Test
    public void testRegisterProduct() throws Exception {

    }
}