package com.kingdangkou.weixin.weixiaodan.token;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;

/**
 * Created by dongy on 2016-12-28.
 */
@Component
public class WeixinMsgSsender {

    @SuppressWarnings("resource")
    public String sendGet(Map<String, String> params)throws Exception {
        InputStream inputStream = null;
        HttpGet request = new HttpGet();
        try {
            String url = buildUrl(params);
            HttpClient client = new DefaultHttpClient();

            request.setHeader("Accept-Encoding", "gzip");
            request.setURI(new URI(url));

            HttpResponse response = client.execute(request);

            inputStream = response.getEntity().getContent();
            String result = getJsonStringFromGZIP(inputStream);
            return result;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            request.releaseConnection();
        }

    }


    private String getJsonStringFromGZIP(InputStream is) {
        String jsonString = null;
        try {
            BufferedInputStream bis = new BufferedInputStream(is);
            bis.mark(2);
            // 取前两个字节
            byte[] header = new byte[2];
            int result = bis.read(header);
            // reset 输入流到开始位置
            bis.reset();
            // 判断是否是 GZIP 格式
            int headerData = getShort(header);
            // Gzip 流 的前两个字节是 0x1f8b
            if (result != -1 && headerData == 0x1f8b) {
                // LogUtil.i("HttpTask", " use GZIPInputStream  ");
                is = new GZIPInputStream(bis);
            } else {
                // LogUtil.d("HttpTask", " not use GZIPInputStream");
                is = bis;
            }
            InputStreamReader reader = new InputStreamReader(is, "utf-8");
            char[] data = new char[100];
            int readSize;
            StringBuffer sb = new StringBuffer();
            while ((readSize = reader.read(data)) > 0) {
                sb.append(data, 0, readSize);
            }
            jsonString = sb.toString();
            bis.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonString;
    }

    private int getShort(byte[] data) {
        return (data[0] << 8) | data[1] & 0xFF;
    }


    public String buildUrl(Map<String, String> params) {
        String reqUrl = params.get("url");
        params.remove("url");
        StringBuilder query = new StringBuilder();
        Set<String> set = params.keySet();
        for (String key : set) {
            query.append(String.format("%s=%s&", key, params.get(key)));
        }
        return reqUrl + "?" + query.toString();
    }
}
