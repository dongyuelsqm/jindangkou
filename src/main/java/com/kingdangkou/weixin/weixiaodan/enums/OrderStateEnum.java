package com.kingdangkou.weixin.weixiaodan.enums;

/**
 * Created by dongy on 2016-11-26.
 */
public enum  OrderStateEnum {
    NOT_PAY(0), TO_TRANSIT(1), IN_TRANSIT(2), COMPLETE(3), AFTER_SALES(4);

    OrderStateEnum(int value) {
        this.value = value;
    }

    private int value;
    public int getValue(){
        return value;
    }
    public static OrderStateEnum getEnum(int value){
        for (OrderStateEnum target: values()){
            if (target.value == value){
                return target;
            }
        }
        return null;
    }
}
