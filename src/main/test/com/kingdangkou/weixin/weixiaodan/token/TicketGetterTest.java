package com.kingdangkou.weixin.weixiaodan.token;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by dongy on 2017-02-28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spitter-servlet.xml")
public class TicketGetterTest {
    @Autowired
    private TicketGetter getter;

    @Test
    public void getTickets() throws Exception {
        String ticket = getter.getTickets("Gw8DVJdwXAFzY4ZKaEql7qNPlFO4wklIi_HKGZOUPhUoaRT3Um5IAYDcbqido7uB4i7ElfEdcnWCcccOwHGb9KAJj1zZuWtktZgleiwjfSMOSAdAGACGO");
        System.out.println(ticket);
    }

}