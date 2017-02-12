package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.CustomerDao;
import com.kingdangkou.weixin.weixiaodan.entity.CustomerEntity;
import com.kingdangkou.weixin.weixiaodan.model.Failure;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.token.AccessTokenHolder;
import com.kingdangkou.weixin.weixiaodan.token.CustomerInfoGetter;
import com.kingdangkou.weixin.weixiaodan.token.WeixinMsgSsender;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by dongy on 2017-01-12.
 */
@Service
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private CustomerInfoGetter customerInfoGetter;
    @Autowired
    private AccessTokenHolder accessTokenHolder;

    @Autowired
    private WeixinMsgSsender weixinMsgSsender;

    public void save(Result result) throws Exception {
        String openid = (String) result.getDetail();
        CustomerEntity customerEntity = customerDao.get(CustomerEntity.class, openid);
        if (customerEntity != null){
//            CustomerEntity entity = customerInfoGetter.sendGet(accessTokenHolder.getAccessToken(), openid);
//            customerDao.save(entity);
        }
    }

    public Result get(String code) throws Exception {
        JSONObject jsonObject = getAccessTakenMsg(code);
        if (jsonObject.getString("access_token") == null) return new Failure("invalie code");
        String json = customerInfoGetter.sendGet(jsonObject.getString("access_token"), jsonObject.getString("openid"));
        return new Result(true, json);
    }

    private JSONObject getAccessTakenMsg(String code) throws Exception {
        HashMap<String, String> params = new HashMap<>();
        params.put("code", code);
        params.put("grant_type", "authorization_code");
        params.put("url", "https://api.weixin.qq.com/sns/oauth2/access_token");
        params.put("appId", "wx94e43e5190dbb1e1");
        params.put("secret", "0b3a8e030bdcb6260aa44480049b8e6d");
        String json = weixinMsgSsender.sendGet(params);
        return JSONObject.fromObject(json);
    }
}
