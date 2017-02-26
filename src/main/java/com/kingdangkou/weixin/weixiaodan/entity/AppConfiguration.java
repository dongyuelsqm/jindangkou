package com.kingdangkou.weixin.weixiaodan.entity;

import com.kingdangkou.weixin.weixiaodan.utils.configs.ConfigFileReader;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

/**
 * Created by dongy on 2017-02-07.
 */
@Component
public class AppConfiguration {
    private AppInfo appInfo;
    private String mch_id;
    private String key;
    private String notifyUrl;

    public AppConfiguration() throws FileNotFoundException {
        appInfo = (AppInfo) ConfigFileReader.getConfigurationData("app_info.xml");
        AppConfiguration appConfiguration = (AppConfiguration) ConfigFileReader.getConfigurationData("PaymentInfo.xml");
        this.mch_id = appConfiguration.mch_id;
        this.key = appConfiguration.key;
        this.notifyUrl = appConfiguration.notifyUrl;
    }

    public String getAppId() {
        return appInfo.getAppid();
    }

    public void setAppId(String appId) {
        this.appInfo.setAppid(appId);
    }

    public String getSecret() {
        return appInfo.getSecret();
    }

    public void setSecret(String secret) {
        this.appInfo.setSecret(secret);
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}
