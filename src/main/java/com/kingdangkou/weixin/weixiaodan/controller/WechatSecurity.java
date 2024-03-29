package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.message.TextMessage;
import com.kingdangkou.weixin.weixiaodan.utils.MsgUtil;
import com.kingdangkou.weixin.weixiaodan.utils.SHA1;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * Created by dongy on 2016-11-10.
 */
@Controller
@RequestMapping("/access")
public class WechatSecurity {
    private static Logger logger = Logger.getLogger(WechatSecurity.class);

    private static final String TOKEN = "TOKEN";
    /**
     *
     * @Description: 用于接收 get 参数，返回验证参数
     * @param @param request
     * @param @param response
     * @param @param signature
     * @param @param timestamp
     * @param @param nonce
     * @param @param echostr
     * @author dapengniao
     * @date 2016 年 3 月 4 日 下午 6:20:00
     */
    @RequestMapping(method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("received the security msg!");
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");

        String[] str = { TOKEN, timestamp, nonce };
        Arrays.sort(str); // 字典序排序
        String bigStr = str[0] + str[1] + str[2];
        // SHA1加密
        String digest = new SHA1().getDigestOfString(bigStr.getBytes())
                .toLowerCase();

        // 确认请求来至微信
        if (digest.equals(signature)) {
            response.getWriter().print(echostr);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/url")
    public void postMsg(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String> map = MsgUtil.parseXml(request);
        String openid=map.get("FromUserName"); //用户 openid
        String mpid=map.get("ToUserName");   //公众号原始 ID
        TextMessage txtmsg=new TextMessage();
        txtmsg.setToUserName(openid);
        txtmsg.setFromUserName(mpid);
        txtmsg.setCreateTime(new Date().getTime());
        txtmsg.setMsgType("transfer_customer_service");
        String xml = MsgUtil.textMessageToXml(txtmsg);
        response.getWriter().print(xml);
    }
}
