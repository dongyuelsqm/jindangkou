package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.CustomerDao;
import com.kingdangkou.weixin.weixiaodan.entity.AppInfo;
import com.kingdangkou.weixin.weixiaodan.model.Failure;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.token.WeixinMsgSsender;
import com.kingdangkou.weixin.weixiaodan.utils.configs.ConfigFileReader;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * Created by dongy on 2017-01-12.
 */
@Service
public class CustomerService {
    private final String USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo";
    private final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private WeixinMsgSsender weixinMsgSsender;

    private AppInfo appInfo;


    public CustomerService() throws FileNotFoundException {
        appInfo = (AppInfo) ConfigFileReader.getConfigurationData("app_info.xml");
    }

    public Result get(String code) throws Exception {
        JSONObject jsonObject = sendGetAccessToken(code);
        if (!jsonObject.containsKey("access_token")) return new Failure("invalie code");
        String json = sendGetCustomerInfo(jsonObject.getString("access_token"), jsonObject.getString("openid"));
        return new Result(true, json);
    }

    public String sendGetCustomerInfo(String access_token, String openid) throws Exception {
        HashMap<String, String> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("openid", openid);
        map.put("lang", "zh_CN");
        map.put("url", USER_INFO_URL);
        return weixinMsgSsender.sendGet(map);
    }

    private JSONObject sendGetAccessToken(String code) throws Exception {
        HashMap<String, String> params = new HashMap<>();
        params.put("code", code);
        params.put("grant_type", "authorization_code");
        params.put("url", ACCESS_TOKEN_URL);
        params.put("appId", appInfo.getAppid());
        params.put("secret", appInfo.getSecret());
        String json = weixinMsgSsender.sendGet(params);
        return JSONObject.fromObject(json);
    }

    public AppInfo getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(AppInfo appInfo) {
        this.appInfo = appInfo;
    }
}
