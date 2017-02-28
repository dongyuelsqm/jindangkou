package com.kingdangkou.weixin.weixiaodan.tools.express.kuaidiniao;

/**
 * Created by dongy on 2017-02-27.
 */
public enum PayType {
    Prepaia(1), Collect(2), Monthly(3), ThirdPart(4);

    private int value;
    PayType(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
