package com.kingdangkou.weixin.weixiaodan.token;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
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
    private String ticket;
    private static int counter = 0;
    Timer timer = new Timer();
    @Autowired
    AppInfoHolder appInfoHolder;

    @Autowired
    private WeixinMsgSender weixinMsgSender;

    @Autowired
    private TicketGetter ticketGetter;

    public AccessTokenHolder() throws ParserConfigurationException, SAXException, IOException {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                counter++;
                try {
                    HashMap<String, String> params = appInfoHolder.getParams();
                    String s = weixinMsgSender.sendGet(params);
                    accessToken = JSONObject.fromObject(s).getString("access_token");
                    ticket = ticketGetter.getTickets(accessToken);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 1000, period);
    }

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

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
