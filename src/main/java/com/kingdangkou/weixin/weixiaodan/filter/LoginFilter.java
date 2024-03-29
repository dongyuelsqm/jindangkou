package com.kingdangkou.weixin.weixiaodan.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class LoginFilter implements Filter {

    private boolean isDebug;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        isDebug = Boolean.valueOf(filterConfig.getInitParameter("isDebug"));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 获得在下面代码中要用的request,response,session对象
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        HttpSession session = servletRequest.getSession();

        // 获得用户请求的URI
        String path = servletRequest.getRequestURI();
        //System.out.println(path);

        // 从session里取员工工号信息
        String empId = (String) session.getAttribute("boss");

        /*创建类Constants.java，里面写的是无需过滤的页面
        for (int i = 0; i < Constants.NoFilter_Pages.length; i++) {

            if (path.indexOf(Constants.NoFilter_Pages[i]) > -1) {
                chain.doFilter(servletRequest, servletResponse);
                return;
            }
        }*/

        // 登陆页面无需过滤
        if(path.indexOf("/login.jsp") > -1) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (isDebug){
            chain.doFilter(request, response);
            return;
        }
        // 判断如果没有取到员工信息,就跳转到登陆页面
        if (empId == null || "".equals(empId)) {
            // 跳转到登陆页面
            servletResponse.sendRedirect("/weixin/website/login.jsp");
        } else {
            // 已经登陆,继续此次请求
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}