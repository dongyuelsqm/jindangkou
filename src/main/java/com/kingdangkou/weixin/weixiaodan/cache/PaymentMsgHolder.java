package com.kingdangkou.weixin.weixiaodan.cache;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dongy on 2017-03-14.
 */
@Component
public class PaymentMsgHolder {
    private static ConcurrentHashMap<String, String> signs = new ConcurrentHashMap<>();
    public void put(String orderId, String sign){
        signs.put(orderId, sign);
    }

    public boolean checkValidation(String orderId, String sign){
        return sign.equals(signs.get(orderId));
    }

    public void remove(String orderId){
        signs.remove(orderId);
    }

}
