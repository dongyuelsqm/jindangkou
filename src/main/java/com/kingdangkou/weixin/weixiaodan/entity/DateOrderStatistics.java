package com.kingdangkou.weixin.weixiaodan.entity;

/**
 * Created by dongy on 2017-03-22.
 */
public class DateOrderStatistics {
    private String time;
    private int orderNumber;
    private int sales;
    private int customerNumber;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
