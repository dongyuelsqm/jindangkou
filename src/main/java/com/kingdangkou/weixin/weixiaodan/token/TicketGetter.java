package com.kingdangkou.weixin.weixiaodan.token;

import net.sf.json.JSONObject;
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
        HashMap<String, String> params = constructMsg(token);
        String s = sender.sendGet(params);
        return parseTicket(s);
    }

    private String parseTicket(String s) {
        return JSONObject.fromObject(s).getString("ticket");
    }

    private HashMap<String, String> constructMsg(String token) {
        HashMap<String, String> params = new HashMap<>();
        params.put("url", URL);
        params.put("access_token", token);
        params.put("type", "jsapi");
        return params;
    }
}
