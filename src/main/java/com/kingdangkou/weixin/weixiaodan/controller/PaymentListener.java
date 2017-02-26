package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.entity.PayCallback;
import com.kingdangkou.weixin.weixiaodan.service.OrderService;
import com.kingdangkou.weixin.weixiaodan.service.utils.XmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;

import static com.kingdangkou.weixin.weixiaodan.enums.OrderStateEnum.TO_TRANSIT;

/**
 * Created by dongy on 2017-02-07.
 */
@Controller
@RequestMapping(value = "/wxpay/pay.action")
public class PaymentListener {
    @Autowired
    private OrderService orderService;

    @Autowired
    private XmlUtil xmlUtil;
    @ResponseBody
    @RequestMapping(value="wechat_notify")
    public String wechatPayNotify(HttpServletRequest request){
        try {
            Map<String, String> map = getCallbackParams(request);
            if (map.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
                String orderId = map.get("out_trade_no");
                String weixinTransactionId = map.get("transaction_id");
                //这里写成功后的业务逻辑
                orderService.updateStateAndTransactionId(orderId, String.valueOf(TO_TRANSIT.getValue()), weixinTransactionId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return getPayCallback();
    }

    public Map<String, String> getCallbackParams(HttpServletRequest request)
            throws Exception {
        InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        System.out.println("~~~~~~~~~~~~~~~~付款成功~~~~~~~~~");
        outSteam.close();
        inStream.close();
        String result = new String(outSteam.toByteArray(), "utf-8");
        return xmlUtil.xml2Map(result);
    }

    public String getPayCallback(){
        PayCallback callback = new PayCallback();
        String xml = xmlUtil.toXML("xml", callback);
        return xml;
    }
}
