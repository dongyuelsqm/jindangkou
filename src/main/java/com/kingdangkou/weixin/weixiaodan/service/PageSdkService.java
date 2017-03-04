package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.entity.AppConfiguration;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.token.AccessTokenHolder;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by dongy on 2017-02-28.
 */
@Service
public class PageSdkService {
    @Autowired
    private AccessTokenHolder accessTokenHolder;

    @Autowired
    private AppConfiguration appConfiguration;
    public Result getSdkInfo(String url) {
        String ticket = accessTokenHolder.getTicket();
        System.out.println(ticket);
        String appId = appConfiguration.getAppId();
        String timestamp = String.valueOf(System.currentTimeMillis()/1000);
        String nonceStr = UUID.randomUUID().toString().substring(0, 30);
        String signature = getSignature(ticket, timestamp, nonceStr, url);
        JSONObject json = new JSONObject();
        json.put("appId", appId);
        json.put("timestamp", timestamp);
        json.put("nonceStr", nonceStr);
        json.put("signature", signature);
        json.put("url", url);
        json.put("ticket", ticket);
        return new Result(true, json);
    }

    private String getSignature(String ticket, String timestamp, String nonceStr, String url) {
        StringBuffer sb = new StringBuffer();
        sb.append("jsapi_ticket=").append(ticket);
        sb.append("&nonceStr=").append(nonceStr);
        sb.append("&timestamp=").append(timestamp);
        sb.append("&url=").append(url);
        System.out.println(sb);
        return DigestUtils.sha1Hex(sb.toString());
    }
}
