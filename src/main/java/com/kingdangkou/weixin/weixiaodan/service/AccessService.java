package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.token.AccessTakenGetter;
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
    private AccessTakenGetter accessTakenGetter;
    
    @Autowired
    private AppInfoHolder appInfoHolder;
    public Result getOpenID(String code) throws Exception {
        HashMap<String, String> params = appInfoHolder.getParams();
        params.put("code", code);
        String json = accessTakenGetter.sendGet(params);
        JSONObject jsonObject = JSONObject.fromObject(json);
        return new Result(true, jsonObject.get("openid").toString());
    }
}
