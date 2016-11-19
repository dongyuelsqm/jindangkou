package com.kingdangkou.weixin.weixiaodan.model;

import org.springframework.stereotype.Component;

/**
 * Created by dongy on 2016-11-19.
 */
@Component
public class Result {
    private boolean isSuccess;
    private String detail;

    public Result() {
    }

    public Result(boolean isSuccess, String detail) {
        this.isSuccess = isSuccess;
        this.detail = detail;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
