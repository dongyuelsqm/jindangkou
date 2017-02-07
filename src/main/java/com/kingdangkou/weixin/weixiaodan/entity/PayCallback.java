package com.kingdangkou.weixin.weixiaodan.entity;

/**
 * Created by dongy on 2017-02-07.
 */
public class PayCallback {
    private String return_code;
    private String return_msg;

    public PayCallback() {
        this.return_code = "SUCCESS";
        this.return_msg = "OK";
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }
}
