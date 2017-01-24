package com.kingdangkou.weixin.weixiaodan.token;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spitter-servlet.xml")
public class AccessTokenHolderTest {

    @Autowired
    AccessTokenHolder accessTokenHolder;
    @Test
    public void testGetAccess_Token() throws Exception {
        Thread.sleep(10 * 1000);
        String access_token = accessTokenHolder.getAccess_Token();
        assertNotNull(access_token);
    }
}