package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.entity.AppConfiguration;
import com.kingdangkou.weixin.weixiaodan.entity.JsAPIConfig;
import com.kingdangkou.weixin.weixiaodan.entity.UnifiedOrder;
import com.kingdangkou.weixin.weixiaodan.service.utils.XmlUtil;
import com.kingdangkou.weixin.weixiaodan.utils.HttpConnection;
import com.kingdangkou.weixin.weixiaodan.utils.configs.ConfigFileReader;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.UUID;

/**
 * Created by dongy on 2017-02-06.
 */
@Component
public class UnifiedOrderService {
    private String unifiedOrderUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    @Autowired
    private HttpConnection httpConnection;

    @Autowired
    private XmlUtil xmlUtil;

    @Autowired
    private AppConfiguration config;

    public UnifiedOrderService() throws FileNotFoundException {
        config = (AppConfiguration) ConfigFileReader.getConfigurationData("PaymentInfo.xml");

    }

    public String unifiedOrder(String openId, String orderId, float money) throws Exception{
        UnifiedOrder unifiedOrder = new UnifiedOrder();
        unifiedOrder.setAppid(config.getAppId());
        unifiedOrder.setAttach("hehedesk");

        unifiedOrder.setBody("hehedesk");
        unifiedOrder.setMch_id(config.getMch_id());

        String nonce = UUID.randomUUID().toString().substring(0, 30);
        unifiedOrder.setNonce_str(nonce);
        unifiedOrder.setNotify_url(config.getNotifyUrl());

        unifiedOrder.setOpenid(openId);
        unifiedOrder.setOut_trade_no(orderId);

        unifiedOrder.setSpbill_create_ip("14.23.150.211");
        unifiedOrder.setTotal_fee(money);

        String sign = createUnifiedOrderSign(unifiedOrder);
        unifiedOrder.setSign(sign);

        /**
         * 转成XML格式
         */
        String xml = xmlUtil.toXML("xml", unifiedOrder);
        String response = httpConnection.post(unifiedOrderUrl, xml);
//        logger.info("unifiedOrder");
//        logger.info(response);
        // TODO: 2017-02-06 should add logger here
        Map<String, String> responseMap = xmlUtil.parseXml(response);
        return responseMap.get("prepay_id");
    }

    public JsAPIConfig createPayConfig(String prepayId) throws Exception {
        JsAPIConfig jsAPIConfig = new JsAPIConfig();

        String nonce = UUID.randomUUID().toString();
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String packageName = "prepay_id="+prepayId;
        StringBuffer sign = new StringBuffer();
        sign.append("appId=").append(config.getAppId());
        sign.append("&nonceStr=").append(nonce);
        sign.append("&package=").append(packageName);
        sign.append("&signType=").append(jsAPIConfig.getSignType());
        sign.append("&timeStamp=").append(timestamp);
        sign.append("&key=").append(config.getKey());
        String signature = DigestUtils.md5Hex(sign.toString()).toUpperCase();

        jsAPIConfig.setAppId(config.getAppId());
        jsAPIConfig.setNonce(nonce);
        jsAPIConfig.setTimestamp(timestamp);
        jsAPIConfig.setPackageName(packageName);
        jsAPIConfig.setSignature(signature);

        return jsAPIConfig;
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
        sign.append("&key=").append("sasas");
        return DigestUtils.md5Hex(sign.toString()).toUpperCase();
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
