package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.OrderDao;
import org.junit.Test;

public class OrderServiceTest {

    @Test
    public void testSave() throws Exception {
        OrderService service = new OrderService();
        service.setOrderDao(new OrderDao());
        String openID = "11";
        String subOrders = "[{\"id\":0,\"number\":1,\"order\":null,\"product_id\":8},{\"id\":0,\"number\":2,\"order\":null,\"product_id\":9}]";
        String address_id = "22";
        service.save(openID, subOrders, address_id);
    }
}