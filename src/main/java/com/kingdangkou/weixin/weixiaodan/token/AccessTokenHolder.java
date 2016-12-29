package com.kingdangkou.weixin.weixiaodan.token;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dongy on 2016-12-28.
 */
public class AccessTokenHolder {
    private int period = 3600 * 1000;
    private String Access_Token;
    @Autowired
    AppInfoHolder appInfoHolder;

    @Autowired
    private AccessTakenGetter accessTakenGetter;

    public AccessTokenHolder(){
        Timer timer = new Timer(true);
        timer.schedule(timerTask, 0, period);
    }

    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            try {
                appInfoHolder.reloadConfiguration();
                Access_Token = accessTakenGetter.sendGet(appInfoHolder.getParams());
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

    public String getAccess_Token() {
        return Access_Token;
    }

    public void setAccess_Token(String access_Token) {
        Access_Token = access_Token;
    }

    public AppInfoHolder getAppInfoHolder() {
        return appInfoHolder;
    }

    public void setAppInfoHolder(AppInfoHolder appInfoHolder) {
        this.appInfoHolder = appInfoHolder;
    }

    public AccessTakenGetter getAccessTakenGetter() {
        return accessTakenGetter;
    }

    public void setAccessTakenGetter(AccessTakenGetter accessTakenGetter) {
        this.accessTakenGetter = accessTakenGetter;
    }

    public TimerTask getTimerTask() {
        return timerTask;
    }

    public void setTimerTask(TimerTask timerTask) {
        this.timerTask = timerTask;
    }
}
