package com.kingdangkou.weixin.weixiaodan.token;

import com.kingdangkou.weixin.weixiaodan.entity.CustomerEntity;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by dongy on 2017-01-12.
 */
@Component
public class CustomerInfoGetter {

    @Autowired
    private AccessTakenGetter accessTakenGetter;
    public CustomerEntity sendGet(String access_token, String openid) throws Exception {
        String url = "https://api.weixin.qq.com/sns/userinfo";
        HashMap<String, String> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("openid", openid);
        map.put("lang", "zh_CN");
        map.put("url", url);
        String json = accessTakenGetter.sendGet(map);
        return new CustomerEntity(JSONObject.fromObject(json));
    }
}
