package com.kingdangkou.weixin.weixiaodan.entity;

/**
 * Created by dongy on 2017-03-22.
 */
public class OrderStatisticsData {
    private String key;
    private int orderNumber;
    private int sales;
    private int customerNumber;

    public OrderStatisticsData(String key, int orderNumber, int sales, int customerNumber) {
        this.key = key;
        this.orderNumber = orderNumber;
        this.sales = sales;
        this.customerNumber = customerNumber;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }
}
