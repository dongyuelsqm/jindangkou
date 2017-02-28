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
    public Result getSdkInfo() {
        String ticket = accessTokenHolder.getTicket();
        System.out.println(ticket);
        String appId = appConfiguration.getAppId();
        String timestamp = String.valueOf(System.currentTimeMillis());
        String nonceStr = UUID.randomUUID().toString().substring(0, 30);
        String signature = getSignature(ticket, timestamp, nonceStr);
        JSONObject json = new JSONObject();
        json.put("appId", appId);
        json.put("timestamp", timestamp);
        json.put("nonceStr", nonceStr);
        json.put("signature", signature);
        return new Result(true, json);
    }

    private String getSignature(String ticket, String timestamp, String nonceStr) {
        StringBuffer sb = new StringBuffer();
        sb.append("&jsapi_ticket=").append(ticket);
        sb.append("&nonceStr=").append(nonceStr);
        sb.append("&timeStamp=").append(timestamp);
        sb.append("&url=").append("http://112.124.115.74/weixin-1.0-SNAPSHOT/access");
        return DigestUtils.sha1Hex(sb.toString());
    }
}
