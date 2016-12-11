package com.kingdangkou.weixin.weixiaodan.enums;

/**
 * Created by dongy on 2016-12-10.
 */
public enum Postal {
    free(0), not_free(1);

    private int value;

    Postal(int value) {
        this.value = value;
    }

    public static Postal getEnum(int value){
        for (Postal postal: Postal.values()){
            if (postal.value == value) return postal;
        }
        return null;
    }



    public static void main(String[] args) {
        Postal postal = Postal.getEnum(1);
        System.out.println(postal.name());
    }
}
