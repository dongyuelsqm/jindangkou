package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.model.CheckModel;
import com.kingdangkou.weixin.weixiaodan.utils.EncoderHandler;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Created by dongy on 2016-11-09.
 */
@Service
public class TakenService {
    public String validate(String wxToken, CheckModel tokenModel){
        String signature = tokenModel.getSignature();
        Long timestamp = tokenModel.getTimestamp();
        Long nonce =tokenModel.getNonce();
        String echostr = tokenModel.getEchostr();
        if(signature!=null&&timestamp!=null&nonce!=null) {
            String[] str = {wxToken, timestamp+"", nonce+""};
            Arrays.sort(str); // 字典序排序
            String bigStr = str[0] + str[1] + str[2];
            // SHA1加密
            String digest = EncoderHandler.encode("SHA1", bigStr).toLowerCase();
            // 确认请求来至微信
            if (digest.equals(signature)) {
                //最好此处将echostr存起来，以后每次校验消息来源都需要用到
                return echostr;
            }
        }
        return "error";
    }
}
