package com.kingdangkou.weixin.weixiaodan.controller.weixin;

import com.kingdangkou.weixin.weixiaodan.controller.TestBase;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by dongy on 2017-02-28.
 */
public class PageSdkInfoControllerTest extends TestBase<PageSdkInfoController>{
    @Test
    public void getPageInfo() throws Exception {
        Thread.sleep(10*1000);
        ResultActions resultActions = mockMvc.perform(get("/weixin/page/get").param("url", "http://112.124.115.74/weixin-1.0-SNAPSHOT/access")).andDo(print());
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void name() throws Exception {
        String str = "jsapi_ticket=kgt8ON7yVITDhtdwci0qeSYZPKhRZUSwJWYf9vzRDrHnolnGhwh0lENbo0R9C3r6yygMdp3rqEtz7nlZTPYR4g&noncestr=67f75a29-bad1-46e2-b6a9-70cd00&timestamp=1488376828&url=http://112.124.115.74/weixin-1.0-SNAPSHOT/access";
        StringBuffer sb = new StringBuffer();
        sb.append("jsapi_ticket=").append("kgt8ON7yVITDhtdwci0qeSYZPKhRZUSwJWYf9vzRDrHnolnGhwh0lENbo0R9C3r6yygMdp3rqEtz7nlZTPYR4g");
        sb.append("&noncestr=").append("67f75a29-bad1-46e2-b6a9-70cd00");
        sb.append("&timestamp=").append("1488376828");
        sb.append("&url=").append("http://112.124.115.74/weixin-1.0-SNAPSHOT/access");
        System.out.println(sb);
        assertEquals(str, sb.toString());
        String sha = DigestUtils.sha1Hex(str);
        System.out.println(sha);
        assertEquals("d938f975cc2fc2445668b382b3ace126eb8f0611", sha);

    }
}