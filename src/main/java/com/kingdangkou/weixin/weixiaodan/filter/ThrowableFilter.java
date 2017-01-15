package com.kingdangkou.weixin.weixiaodan.filter;

import com.kingdangkou.weixin.weixiaodan.model.Failure;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by dongy on 2017-01-15.
 */
public class ThrowableFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        }catch (Throwable ex){
            response.getWriter().print(new Failure(ex.getMessage()));
        }
    }

    @Override
    public void destroy() {

    }
}
