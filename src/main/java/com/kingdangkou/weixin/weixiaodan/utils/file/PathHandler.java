package com.kingdangkou.weixin.weixiaodan.utils.file;

import org.springframework.stereotype.Component;

/**
 * Created by dongy on 2017-01-18.
 */
@Component
public class PathHandler {
    public String getWebInfoPath(){
        String path1 = this.getClass().getClassLoader().getResource("").getPath();
        String substring = path1.substring(0, path1.lastIndexOf("WEB-INF"));
        System.out.println(substring);
        return substring;
    }
}
