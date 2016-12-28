package com.kingdangkou.weixin.weixiaodan.token;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by dongy on 2016-12-28.
 */
@Component
public class AppInfoHolder {
    private HashMap<String, String> params = new HashMap<>();

    public HashMap<String, String> reloadConfiguration() throws ParserConfigurationException, IOException, SAXException {
        File file = ResourceUtils.getFile("classpath:AccessToken.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        NodeList attributes = doc.getElementsByTagName("access_token");
        Element item = (Element) attributes.item(0);
        String grant_type = item.getElementsByTagName("grant_type").item(0).getTextContent();
        String appId = item.getElementsByTagName("appId").item(0).getTextContent();
        String secret = item.getElementsByTagName("secret").item(0).getTextContent();
        String url = item.getElementsByTagName("url").item(0).getTextContent();

        params.put("grant_type", grant_type);
        params.put("appId", appId);
        params.put("secret", secret);
        params.put("url", url);
        return params;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }
}
