package com.kingdangkou.weixin.weixiaodan.enums;

/**
 * Created by dongy on 2016-11-26.
 */
public enum  OrderStateEnum {
    NOT_PAY(0), IN_TRANSIT(1), COMPLETE(2), AFTER_SALES(3);

    OrderStateEnum(int value) {
        this.value = value;
    }

    private int value;
    public static OrderStateEnum getEnum(int value){
        for (OrderStateEnum target: values()){
            if (target.value == value){
                return target;
            }
        }
        return null;
    }
}
