package com.kingdangkou.weixin.weixiaodan.service.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dongy on 2017-02-06.
 */
@Component
public class XmlUtil {
    XStream stream = new XStream(new XppDriver(new NoNameCoder()));

    public String toXML(String alias, Object obj){

        stream.alias(alias, obj.getClass());
        return stream.toXML(obj);
    }

    public <T> T parseXml(String xml, Class<T> cls) {
        XStream stream = new XStream(new XppDriver(new NoNameCoder()));
        stream.alias("express", cls);
        return (T)stream.fromXML(xml);
    }

    public static <T> T parseXml(File xml, Class<T> cls) {
        XStream stream = new XStream(new XppDriver(new NoNameCoder()));
        stream.alias("express", cls);
        return (T)stream.fromXML(xml);
    }

    public Map<String,String> xml2Map(String xml){
        try {
            Map<String,String> maps = new HashMap<String, String>();
            Document document = DocumentHelper.parseText(xml);
            Element root = document.getRootElement();
            List<Element> eles = root.elements();
            for(Element e:eles) {
                maps.put(e.getName(), e.getTextTrim());
            }
            return maps;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
