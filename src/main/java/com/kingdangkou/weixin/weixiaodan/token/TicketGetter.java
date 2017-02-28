package com.kingdangkou.weixin.weixiaodan.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by dongy on 2017-02-28.
 */
@Component
public class TicketGetter {
    private static final String URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
    @Autowired
    private WeixinMsgSender sender;

    public String getTickets(String token) throws Exception {
        HashMap<String, String> params = new HashMap<>();
        params.put("url", URL);
        params.put("access_token", token);
        params.put("type", "jsapi");
        return sender.sendGet(params);
    }
}
