package com.kingdangkou.weixin.weixiaodan.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by dongy on 2016-11-22.
 */
public class FileAop {
    //Logger logger = Logger.getLogger(FileAop.class.getName());
   // @Pointcut("execution (* com.kingdangkou.weixin.weixiaodan.utils.FileHandler.saveFile(HttpServletRequest, HttpServletResponse)) && args(request, response))")
    private void saveFile(HttpServletRequest request, HttpServletResponse response){}

   // @Before("saveFile(request, response)")
    public void check(HttpServletRequest request, HttpServletResponse response){
       // logger.info("received product register:" + request.getParameter(""));
    }
}
