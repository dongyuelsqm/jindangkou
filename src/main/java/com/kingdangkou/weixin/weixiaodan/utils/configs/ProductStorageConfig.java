package com.kingdangkou.weixin.weixiaodan.utils.configs;

import net.sf.json.JsonConfig;
import org.springframework.stereotype.Component;

/**
 * Created by dongy on 2017-01-16.
 */
@Component
public class ProductStorageConfig extends JsonConfig{
    public ProductStorageConfig() {
        setExcludes(new String[]{"storage"});
    }
}
