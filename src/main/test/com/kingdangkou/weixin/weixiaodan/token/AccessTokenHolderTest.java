package com.kingdangkou.weixin.weixiaodan.token;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class AccessTokenHolderTest {

    @Test
    public void testGetAccess_Token() throws Exception {
        AccessTokenHolder holder = new AccessTokenHolder();
        Thread.sleep(10 * 1000);
        String access_token = holder.getAccess_Token();
        assertNotNull(access_token);
    }
}