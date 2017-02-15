package com.kingdangkou.weixin.weixiaodan.token;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dongy on 2016-12-28.
 */
@Component
public class AccessTokenHolder {
    private int period = 3600 * 1000;
    private String accessToken;
    @Autowired
    AppInfoHolder appInfoHolder;

    @Autowired
    private WeixinMsgSender weixinMsgSender;

    public AccessTokenHolder(){
        Timer timer = new Timer(true);
        timer.schedule(timerTask, 1000, period);
    }

    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            try {
                HashMap<String, String> params = appInfoHolder.getParams();
                String s = weixinMsgSender.sendGet(params);
                accessToken = JSONObject.fromObject(s).getString("access_token");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public AppInfoHolder getAppInfoHolder() {
        return appInfoHolder;
    }

    public void setAppInfoHolder(AppInfoHolder appInfoHolder) {
        this.appInfoHolder = appInfoHolder;
    }

    public WeixinMsgSender getWeixinMsgSender() {
        return weixinMsgSender;
    }

    public void setWeixinMsgSender(WeixinMsgSender weixinMsgSender) {
        this.weixinMsgSender = weixinMsgSender;
    }

    public TimerTask getTimerTask() {
        return timerTask;
    }

    public void setTimerTask(TimerTask timerTask) {
        this.timerTask = timerTask;
    }
}
