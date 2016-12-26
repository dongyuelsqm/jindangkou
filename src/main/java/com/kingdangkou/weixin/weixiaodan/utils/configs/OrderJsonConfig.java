package com.kingdangkou.weixin.weixiaodan.utils.configs;

import net.sf.json.JsonConfig;

/**
 * Created by dongy on 2016-12-26.
 */
public class OrderJsonConfig extends JsonConfig{
    public OrderJsonConfig() {
        setExcludes(new String[]{"order"});
    }
}
