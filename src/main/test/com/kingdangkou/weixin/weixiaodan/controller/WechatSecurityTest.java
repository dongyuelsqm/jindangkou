package com.kingdangkou.weixin.weixiaodan.controller;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class WechatSecurityTest {

    @Test
    public void testDoGet() throws Exception {
        WechatSecurity security = new WechatSecurity();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("signature", "49e5ca02bcf1f61ccac87c8566cb59286048e9a0");
        request.setParameter("echostr", "5193240246988045819");
        request.setParameter("timestamp", "1482248434");
        request.setParameter("nonce", "994101893");
        MockHttpServletResponse response = new MockHttpServletResponse();
        security.doGet(request, response);
    }
}