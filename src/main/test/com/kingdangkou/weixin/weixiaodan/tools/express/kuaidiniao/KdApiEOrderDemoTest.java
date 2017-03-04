package com.kingdangkou.weixin.weixiaodan.tools.express.kuaidiniao;

import com.kingdangkou.weixin.weixiaodan.entity.Address;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

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
        Commodity commodity = new Commodity();
        ArrayList<Commodity> commodities = new ArrayList<>();
        commodities.add(commodity);
        Address address = new Address("name", "mobile", "openid", "province", "city", "are", "address");
        ExpressOrder order = new ExpressOrder("1", "EMS", PayType.Prepaid.getValue(), 1, commodities, address, address, 0, 1);
        System.out.println(JSONObject.fromObject(order));
        String s = demo.orderOnlineByJson(order);
        System.out.println(s);
    }

    @Test
    public void testJson(){
        ExpressOrder order = new ExpressOrder();
        JSONObject object = JSONObject.fromObject(order);
        System.out.println(object.toString());
//        JSONObject.fromObject()
    }

}