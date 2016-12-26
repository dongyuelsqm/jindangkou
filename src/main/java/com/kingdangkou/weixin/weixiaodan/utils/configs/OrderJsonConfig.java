package com.kingdangkou.weixin.weixiaodan.utils.configs;

import net.sf.json.JsonConfig;
import org.springframework.stereotype.Component;

/**
 * Created by dongy on 2016-12-26.
 */
@Component
public class OrderJsonConfig extends JsonConfig{
    public OrderJsonConfig() {
        setExcludes(new String[]{"order"});
    }
}
