package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.entity.AppConfiguration;
import com.kingdangkou.weixin.weixiaodan.entity.UnifiedOrder;
import com.kingdangkou.weixin.weixiaodan.service.utils.XmlUtil;
import com.kingdangkou.weixin.weixiaodan.utils.HttpConnection;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.util.Map;
import java.util.UUID;

import static com.kingdangkou.weixin.weixiaodan.utils.SHA1.byteArrayToHexString;

/**
 * Created by dongy on 2017-02-06.
 */
@Component
public class UnifiedOrderService {
    private String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    @Autowired
    private HttpConnection httpConnection;

    @Autowired
    private XmlUtil xmlUtil;

    @Autowired
    private AppConfiguration config;

    public UnifiedOrderService() throws FileNotFoundException {
    }

    public String unifiedOrder(String openId, String orderId, int money, String attach) throws Exception{
        UnifiedOrder unifiedOrder = new UnifiedOrder();
        unifiedOrder.setAppid(config.getAppId());
        unifiedOrder.setAttach(attach);

        unifiedOrder.setBody("hehedesk");
        unifiedOrder.setMch_id(config.getMch_id());

        String nonce = UUID.randomUUID().toString().substring(0, 30);
        unifiedOrder.setNonce_str(nonce);
        unifiedOrder.setNotify_url(config.getNotifyUrl());

        unifiedOrder.setOpenid(openId);
        unifiedOrder.setOut_trade_no(orderId);

        unifiedOrder.setSpbill_create_ip("127.0.0.1");
        unifiedOrder.setTotal_fee(money);

        String sign = createUnifiedOrderSign(unifiedOrder);
        unifiedOrder.setSign(sign);

        String xml = xmlUtil.toXML("xml", unifiedOrder);

        String response = httpConnection.post(url, xml);
        // TODO: 2017-02-06 should add logger here

        Map<String, String> responseMap = xmlUtil.xml2Map(response);
        String prepay_id = responseMap.get("prepay_id");
        prepay_id = prepay_id == null? response:prepay_id;
        return createPayConfig(prepay_id, xml);
    }

    private String createPayConfig(String prepayId, String xml) throws Exception {

        String nonce = UUID.randomUUID().toString();
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String packageName = "prepay_id="+prepayId;
        StringBuffer sign = new StringBuffer();
        sign.append("appId=").append(config.getAppId());
        sign.append("&nonceStr=").append(nonce);
        sign.append("&package=").append(packageName);
        sign.append("&signType=").append("MD5");
        sign.append("&timeStamp=").append(timestamp);
        sign.append("&key=").append(config.getKey());
        System.out.println(sign);
        String signature = MD5Encode(sign.toString()).toUpperCase();

        JSONObject json = new JSONObject();
        json.put("appId", config.getAppId());
        json.put("timeStamp", timestamp);
        json.put("nonceStr", nonce);
        json.put("package", packageName);
        json.put("signType", "MD5");
        json.put("paySign", signature);
        json.put("xml", xml);
        return json.toString();
    }

    public String createUnifiedOrderSign(UnifiedOrder unifiedOrder){
        StringBuffer sign = new StringBuffer();
        sign.append("appid=").append(unifiedOrder.getAppid());
        sign.append("&attach=").append(unifiedOrder.getAttach());
        sign.append("&body=").append(unifiedOrder.getBody());
        sign.append("&device_info=").append(unifiedOrder.getDevice_info());
        sign.append("&mch_id=").append(unifiedOrder.getMch_id());
        sign.append("&nonce_str=").append(unifiedOrder.getNonce_str());
        sign.append("&notify_url=").append(unifiedOrder.getNotify_url());
        sign.append("&openid=").append(unifiedOrder.getOpenid());
        sign.append("&out_trade_no=").append(unifiedOrder.getOut_trade_no());
        sign.append("&spbill_create_ip=").append(unifiedOrder.getSpbill_create_ip());
        sign.append("&total_fee=").append(unifiedOrder.getTotal_fee());
        sign.append("&trade_type=").append(unifiedOrder.getTrade_type());
        sign.append("&key=").append(config.getKey());
        return MD5Encode(sign.toString()).toUpperCase();
    }

    private String MD5Encode(String origin) {
        String resultString = null;
        try {
            resultString = origin;
            MessageDigest md = MessageDigest.getInstance("MD5");
            //resultString = byteArrayToHexString(md.digest(resultString.getBytes())); ///深坑啊，赶紧注释掉，用UTF-8
            resultString = byteArrayToHexString(md.digest(resultString.getBytes("utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }

    public HttpConnection getHttpConnection() {
        return httpConnection;
    }

    public void setHttpConnection(HttpConnection httpConnection) {
        this.httpConnection = httpConnection;
    }

    public XmlUtil getXmlUtil() {
        return xmlUtil;
    }

    public void setXmlUtil(XmlUtil xmlUtil) {
        this.xmlUtil = xmlUtil;
    }
}
