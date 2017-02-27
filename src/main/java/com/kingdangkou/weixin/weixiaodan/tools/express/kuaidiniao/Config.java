package com.kingdangkou.weixin.weixiaodan.tools.express.kuaidiniao;

import com.kingdangkou.weixin.weixiaodan.service.utils.XmlUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by dongy on 2017-02-26.
 */

@Component
public class Config {
    private String EBusinessID;
    private String AppKey;
    private String url;

    public Config() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:express.xml");
        Config config = XmlUtil.parseXml(file, Config.class);
        this.url = config.url;
        this.AppKey = config.AppKey;
        this.EBusinessID = config.EBusinessID;
    }

    public String getEBusinessID() {
        return EBusinessID;
    }

    public void setEBusinessID(String EBusinessID) {
        this.EBusinessID = EBusinessID;
    }

    public String getAppKey() {
        return AppKey;
    }

    public void setAppKey(String appKey) {
        AppKey = appKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
