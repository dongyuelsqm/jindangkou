package com.kingdangkou.weixin.weixiaodan.tools.express.kuaidiniao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by dongy on 2017-02-27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spitter-servlet.xml")
public class KdApiEOrderDemoTest {
    @Autowired
    KdApiEOrderDemo demo;
    @Test
    public void orderOnlineByJson() throws Exception {
        String s = demo.orderOnlineByJson();
        System.out.println(s);
    }

}