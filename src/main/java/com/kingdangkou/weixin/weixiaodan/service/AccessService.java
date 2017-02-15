package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.entity.AppInfo;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.token.WeixinMsgSender;
import com.kingdangkou.weixin.weixiaodan.token.AppInfoHolder;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by dongy on 2016-12-28.
 */
@Service
public class AccessService {
    @Autowired
    private WeixinMsgSender weixinMsgSender;

    private AppInfo appInfo;

    private String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
    private String grantType = "authorization_code";
    
    @Autowired
    private AppInfoHolder appInfoHolder;
    public Result getOpenID(String code) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("code", code);
        params.put("grant_type", grantType);
        params.put("url", url);
        params.put("appId", "wx94e43e5190dbb1e1");
        params.put("secret", "0b3a8e030bdcb6260aa44480049b8e6d");
        String json = weixinMsgSender.sendGet(params);
        JSONObject jsonObject = JSONObject.fromObject(json);
        return new Result(true, jsonObject.get("openid").toString());
    }

    public WeixinMsgSender getWeixinMsgSender() {
        return weixinMsgSender;
    }

    public void setWeixinMsgSender(WeixinMsgSender weixinMsgSender) {
        this.weixinMsgSender = weixinMsgSender;
    }

    public AppInfo getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(AppInfo appInfo) {
        this.appInfo = appInfo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public AppInfoHolder getAppInfoHolder() {
        return appInfoHolder;
    }

    public void setAppInfoHolder(AppInfoHolder appInfoHolder) {
        this.appInfoHolder = appInfoHolder;
    }
}
