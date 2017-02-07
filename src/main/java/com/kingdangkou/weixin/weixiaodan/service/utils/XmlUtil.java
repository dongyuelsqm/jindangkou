package com.kingdangkou.weixin.weixiaodan.service.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.springframework.stereotype.Component;

import java.io.Writer;
import java.util.Map;

/**
 * Created by dongy on 2017-02-06.
 */
@Component
public class XmlUtil {
    XStream stream = new XStream(new XppDriver(new NoNameCoder()) {

        @Override
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @Override
                @SuppressWarnings("rawtypes")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                @Override
                public String encodeNode(String name) {
                    return name;
                }


                @Override
                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });

    public String toXML(String alias, Object obj){

        stream.alias(alias, obj.getClass());
        return stream.toXML(obj);
    }

    public Map<String, String> parseXml(String response) {

        return (Map<String, String>) stream.fromXML(response);
    }
}
