package com.kingdangkou.weixin.weixiaodan.token;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spitter-servlet.xml")
public class AccessTakenGetterTest {

    @Autowired
    AccessTakenGetter accessTakenGetter;
    @Test
    public void testSendGet() throws Exception {
        HashMap<String, String> params = new HashMap<>();
        params.put("grant_type", "client_credential");
        params.put("appid", "wxb5d861c9e4558825");
        params.put("secret", "e6aaf6faf0442514c511f822c8f10ff8");
        params.put("url", "https://api.weixin.qq.com/cgi-bin/token");
        String access_token = accessTakenGetter.sendGet(params);
        System.out.println(access_token);
    }
}