package com.kingdangkou.weixin.weixiaodan.utils.configs;

import net.sf.json.JsonConfig;
import org.springframework.stereotype.Component;

/**
 * Created by dongy on 2016-12-22.
 */
@Component
public class ProductJsonConfig extends JsonConfig {
    public ProductJsonConfig() {
        setExcludes(new String[]{"productEntity"});
    }
}
