package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.OrderDao;
import com.kingdangkou.weixin.weixiaodan.entity.Order;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spitter-servlet.xml")
public class OrderServiceTest {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("file:src/main/webapp/WEB-INF/spitter-servlet.xml");
    OrderService service =(OrderService) ctx.getBean("orderService");

    @Ignore
    @Test
    public void testSave() throws Exception {

        service.setOrderDao(new OrderDao());
        String openID = "11";
        String subOrders = "[{\"id\":0,\"number\":1,\"order\":null,\"product_id\":8},{\"id\":0,\"number\":2,\"order\":null,\"product_id\":9}]";
        String address_id = "2";
        service.save(openID, subOrders, address_id);
    }

    @Test
    public void testGet() throws Exception {
        Order orderModel = service.get("11");
        JsonConfig config = new JsonConfig();
        config.setExcludes(new String[]{"order"});
        JSONObject jsonObject = JSONObject.fromObject(orderModel, config);
        System.out.println(jsonObject);
    }
}