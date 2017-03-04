package com.kingdangkou.weixin.weixiaodan.tools.express.kuaidiniao;

import com.kingdangkou.weixin.weixiaodan.entity.Address;

/**
 * Created by dongy on 2017-02-28.
 */
public class ExpressOrder {
    private String OrderCode;
    private String ShipperCode;
    private int PayType;
    private int ExpType;
    private int IsReturnPrintTemplate;
    private int IsNotice;
    private Address sender = new Address("11", "11", "opend", "opend", "opend", "opend", "opend");
    private Address receiver;
    public ExpressOrder(){}
    public ExpressOrder(String orderCode, String shipperCode, int payType, int expType, Address sender, Address receiver, int isReturnPrintTemplate, int isNotice) {
        OrderCode = orderCode;
        ShipperCode = shipperCode;
        PayType = payType;
        ExpType = expType;
        this.receiver = receiver;
        this.sender = sender;
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

    public Address getSender() {
        return sender;
    }

    public void setSender(Address sender) {
        this.sender = sender;
    }

    public Address getReceiver() {
        return receiver;
    }

    public void setReceiver(Address receiver) {
        this.receiver = receiver;
    }
}
