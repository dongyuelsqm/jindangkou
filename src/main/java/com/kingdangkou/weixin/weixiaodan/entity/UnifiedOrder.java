package com.kingdangkou.weixin.weixiaodan.entity;

import javax.persistence.*;

/**
 * Created by dongy on 2017-02-06.
 */
@Entity
@Table(name = "unified_order")
public class UnifiedOrder {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * 公众账号ID
     */
    @Column(name = "app_id")
    private String appid;
    /**
     * 商户号
     */
    @Column(name = "mch_id")
    private String mch_id;
    /**
     * 附加数据(说明)
     */
    @Column(name = "attach")
    private String attach;
    /**
     * 商品描述
     */
    @Column(name = "body")
    private String body;
    /**
     * 随机串
     */
    @Column(name = "nonce_str")
    private String nonce_str;
    /**
     * 通知地址
     */
    @Column(name = "notify_url")
    private String notify_url;
    /**
     * 用户标识
     */
    @Column(name = "openid")
    private String openid;
    /**
     * 商户订单号
     */
    @Column(name = "out_trade_no")
    private String out_trade_no;
    /**
     * 终端IP（用户）
     */
    @Column(name = "spbill_create_ip")
    private String spbill_create_ip;
    /**
     * 总金额
     */
    @Column(name = "total_fee")
    private float total_fee;
    /**
     * 交易类型
     */
    @Column(name = "trade_type")
    private String trade_type;
    /**
     * 签名
     */
    @Column(name = "sign")
    private String sign;
    /**
     * WEB
     */
    @Column(name = "device_info")
    private String device_info;

    public UnifiedOrder(){
        this.trade_type = "JSAPI";
        this.device_info = "WEB";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public float getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(float total_fee) {
        this.total_fee = total_fee;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }
}
