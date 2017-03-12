package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.entity.AppConfiguration;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.service.utils.Sign;
import com.kingdangkou.weixin.weixiaodan.token.AccessTokenHolder;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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
        url = url.replaceAll("%3A", ":").replaceAll("%2F", "/");
        Map<String, String> signs = Sign.sign(ticket, url);
        String appId = appConfiguration.getAppId();
        String timestamp = signs.get("timestamp");
        String nonceStr = signs.get("nonceStr");
        String signature = signs.get("signature");
        JSONObject json = new JSONObject();
        json.put("appId", appId);
        json.put("timestamp", timestamp);
        json.put("nonceStr", nonceStr);
        json.put("signature", signature);
        json.put("url", url);
        json.put("ticket", ticket);
        json.put("token", accessTokenHolder.getAccessToken());
        return new Result(true, json);
    }

}
