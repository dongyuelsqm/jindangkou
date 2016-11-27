package com.kingdangkou.weixin.weixiaodan.aop;

import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;


/**
 * Created by dongy on 2016-11-22.
 */
public class FileAop {
    Logger logger = Logger.getLogger(FileAop.class.getName());
   @Pointcut("execution (* com.kingdangkou.weixin.weixiaodan.utils.FileHandler.saveFile(..)) && args(request, response))")
    private void saveFile(HttpServletRequest request, HttpServletResponse response){}

    @Before("saveFile(request, response)")
    public void check(HttpServletRequest request, HttpServletResponse response){
       logger.info("received product register:" + request.getParameter(""));
    }
}
