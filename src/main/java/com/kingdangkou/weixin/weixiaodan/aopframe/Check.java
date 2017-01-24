package com.kingdangkou.weixin.weixiaodan.aopframe;

/**
 * Created by dongy on 2017-01-20.
 */
public @interface Check {
    public boolean notNull() default false;
    public long minNum() default 1;
}
