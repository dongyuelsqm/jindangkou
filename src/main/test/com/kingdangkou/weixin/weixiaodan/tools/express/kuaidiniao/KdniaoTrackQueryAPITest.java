package com.kingdangkou.weixin.weixiaodan.tools.express.kuaidiniao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

/**
 * Created by dongy on 2017-02-26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spitter-servlet.xml")
public class KdniaoTrackQueryAPITest {
    @Autowired
    private KdniaoTrackQueryAPI api;
    @Test
    public void getOrderTracesByJson() throws Exception {
        String ems = api.getOrderTracesByJson("EMS", "1071021118418");
        System.out.println(ems);
        assertTrue(ems.contains("北京"));
    }

}