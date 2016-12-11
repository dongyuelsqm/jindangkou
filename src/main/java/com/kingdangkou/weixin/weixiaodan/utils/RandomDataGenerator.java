package com.kingdangkou.weixin.weixiaodan.utils;

/**
 * Created by dongy on 2016-12-10.
 */
public class RandomDataGenerator {
    public static int generate(){
        double random = Math.random();
        return (int)(random * 10000000);
    }
}
