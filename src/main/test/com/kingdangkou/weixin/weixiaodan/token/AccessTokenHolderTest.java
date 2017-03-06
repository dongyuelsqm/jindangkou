package com.kingdangkou.weixin.weixiaodan.token;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spitter-servlet.xml")
public class AccessTokenHolderTest {

    @Autowired
    AccessTokenHolder accessTokenHolder;
    @Test
    public void testGetAccess_Token() throws Exception {
        Thread.sleep(30 * 1000);
        String access_token = accessTokenHolder.getAccessToken();
        String ticket = accessTokenHolder.getTicket();
        assertNotNull(access_token);
        System.out.println(access_token);
        System.out.println(ticket);
        int counter = accessTokenHolder.getCounter();
        System.out.println(counter);
    }


}