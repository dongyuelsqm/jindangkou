package com.kingdangkou.weixin.weixiaodan.utils;

import org.springframework.stereotype.Component;

/**
 * Created by dongy on 2016-11-22.
 */
@Component
public class JustForAopLearn {
    public void testMethod(String param){
        System.out.println(param);
    }

    public void controlMethod(){
        System.out.println("control");
    }
}
