package com.kingdangkou.weixin.weixiaodan.tools.express.kuaidiniao;

/**
 * Created by dongy on 2017-02-28.
 */
public class ExpressOrder {
    private String OrderCode;
    private String ShipperCode;
    private int PayType;
    private int ExpType;
    private String Sender;
    private String Receiver;
    private int IsReturnPrintTemplate;
    private int IsNotice;

    public ExpressOrder(String orderCode, String shipperCode, int payType, int expType, String sender, String receiver, int isReturnPrintTemplate, int isNotice) {
        OrderCode = orderCode;
        ShipperCode = shipperCode;
        PayType = payType;
        ExpType = expType;
        Sender = sender;
        Receiver = receiver;
        IsReturnPrintTemplate = isReturnPrintTemplate;
        IsNotice = isNotice;
    }

    public String getOrderCode() {
        return OrderCode;
    }

    public void setOrderCode(String orderCode) {
        OrderCode = orderCode;
    }

    public String getShipperCode() {
        return ShipperCode;
    }

    public void setShipperCode(String shipperCode) {
        ShipperCode = shipperCode;
    }

    public int getPayType() {
        return PayType;
    }

    public void setPayType(int payType) {
        PayType = payType;
    }

    public int getExpType() {
        return ExpType;
    }

    public void setExpType(int expType) {
        ExpType = expType;
    }

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public String getReceiver() {
        return Receiver;
    }

    public void setReceiver(String receiver) {
        Receiver = receiver;
    }

    public int getIsReturnPrintTemplate() {
        return IsReturnPrintTemplate;
    }

    public void setIsReturnPrintTemplate(int isReturnPrintTemplate) {
        IsReturnPrintTemplate = isReturnPrintTemplate;
    }

    public int getIsNotice() {
        return IsNotice;
    }

    public void setIsNotice(int isNotice) {
        IsNotice = isNotice;
    }
}
