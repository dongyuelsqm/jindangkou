package com.kingdangkou.weixin.weixiaodan.controller.weixin;

import com.kingdangkou.weixin.weixiaodan.controller.TestBase;
import com.kingdangkou.weixin.weixiaodan.token.AccessTokenHolder;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by dongy on 2017-02-28.
 */
public class PageSdkInfoControllerTest extends TestBase<PageSdkInfoController>{
    @Autowired
    private AccessTokenHolder tokenHolder;
    @Test
    public void getPageInfo() throws Exception {
        Thread.sleep(10*1000);
        ResultActions resultActions = mockMvc.perform(get("/weixin/page/get").param("url", "http://www.paimeida.com/web/index3.html?code=011nUarV08E5KW1qj0tV0xGrrV0nUarK&state=STATE")).andDo(print());
        resultActions.andExpect(status().isOk());
        assertEquals(1, tokenHolder.getCounter());
    }

    @Test
    public void name() throws Exception {
        String str = "jsapi_ticket=kgt8ON7yVITDhtdwci0qeSYZPKhRZUSwJWYf9vzRDrGhrSJxaHn8ISShyBBJIWEtXq5WLprxgQhXQ4uPLbcCgg&noncestr=e00949c2-41db-4bb9-b383-daa050&timestamp=1488384589&url=http://paimeida.com/web/";
        StringBuffer sb = new StringBuffer();
        sb.append("jsapi_ticket=").append("kgt8ON7yVITDhtdwci0qeSYZPKhRZUSwJWYf9vzRDrGhrSJxaHn8ISShyBBJIWEtXq5WLprxgQhXQ4uPLbcCgg");
        sb.append("&noncestr=").append("e00949c2-41db-4bb9-b383-daa050");
        sb.append("&timestamp=").append("1488384589");
        sb.append("&url=").append("http://paimeida.com/web/");
        System.out.println(sb);
        assertEquals(str, sb.toString());
        String sha = DigestUtils.sha1Hex(sb.toString());
        System.out.println(sha);
        assertEquals("167e2388f3de1b160f58190899b3154f3001e248", sha);

    }
}