package com.kingdangkou.weixin.weixiaodan.utils.configs;

import com.kingdangkou.weixin.weixiaodan.entity.AppInfo;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by dongy on 2017-02-13.
 */
public class ConfigFileReaderTest {
    @Test
    public void getConfigurationData() throws Exception {
        AppInfo config = (AppInfo) ConfigFileReader.getConfigurationData("app_info.xml");
        assertEquals("wx94e43e5190dbb1e1", config.getAppid());
    }



}