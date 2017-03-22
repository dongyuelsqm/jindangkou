package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.entity.JsAPIConfig;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.json.JsonArray;
import javax.json.JsonObject;

/**
 * Created by dongy on 2017-02-20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spitter-servlet.xml")
public class UnifiedOrderServiceTest {
    @Autowired
    UnifiedOrderService service;

    @Test
    public void unifiedOrder() throws Exception {
        JSONObject jsAPIConfig = service.unifiedOrder("o3Y_kw4pEFSXdVbieWfmGYBJO-bU", "111111", 1, "");
        System.out.println(JSONObject.fromObject(jsAPIConfig));
    }


}