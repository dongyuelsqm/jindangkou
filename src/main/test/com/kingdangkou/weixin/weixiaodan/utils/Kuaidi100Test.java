package com.kingdangkou.weixin.weixiaodan.utils;

import com.kingdangkou.weixin.weixiaodan.entity.KuaidiInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

/**
 * Created by dongy on 2017-02-19.
 */
public class Kuaidi100Test {
    @Autowired
    Kuaidi100 kuaidi100;
    @Test
    public void getLogisticsMsg() throws Exception {
        KuaidiInfo msg = kuaidi100.getLogisticsMsg("1071021118418", "ems");
        assertEquals("ems", msg.getCom());
        assertEquals("0", msg.getState());
    }

}