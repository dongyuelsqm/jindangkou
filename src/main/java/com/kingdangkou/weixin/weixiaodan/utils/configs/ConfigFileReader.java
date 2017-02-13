package com.kingdangkou.weixin.weixiaodan.utils.configs;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

/**
 * Created by dongy on 2017-02-13.
 */
public class ConfigFileReader {
    public static Object getConfigurationData(String path) throws FileNotFoundException {
        XStream stream = new XStream(new XppDriver(new NoNameCoder()));
        return stream.fromXML(ResourceUtils.getFile("classpath:" + path));
    }
}
