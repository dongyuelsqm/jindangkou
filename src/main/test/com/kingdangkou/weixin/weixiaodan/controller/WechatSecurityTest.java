package com.kingdangkou.weixin.weixiaodan.controller;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.Assert.assertEquals;

public class WechatSecurityTest {

    @Test
    public void testDoGet() throws Exception {
        WechatSecurity security = new WechatSecurity();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("signature", "1ac469059c25838777ea82b9d59e895e1c09c112");
        request.setParameter("echostr", "8666780496782319082");
        request.setParameter("timestamp", "1488472360");
        request.setParameter("nonce", "713988545");
        MockHttpServletResponse response = new MockHttpServletResponse();
        security.doGet(request, response);
        assertEquals("8666780496782319082", response.getContentAsString());
    }
}