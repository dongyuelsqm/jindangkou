package com.kingdangkou.weixin.weixiaodan.model;

/**
 * Created by dongy on 2016-12-26.
 */
public class Failure extends Result {
    public Failure(String detail) {
        super(false, detail);
    }
}
