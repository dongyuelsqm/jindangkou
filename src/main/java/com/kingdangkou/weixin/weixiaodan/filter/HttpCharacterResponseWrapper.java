package com.kingdangkou.weixin.weixiaodan.filter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by dongy on 2017-01-04.
 */
public class HttpCharacterResponseWrapper extends HttpServletResponseWrapper {
    private CharArrayWriter ref_charArrayWriter = new CharArrayWriter();
    /**
     * Constructs a response adaptor wrapping the given response.
     *
     * @param response
     * @throws IllegalArgumentException if the response is null
     */
    public HttpCharacterResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(ref_charArrayWriter);
    }

    public CharArrayWriter getRef_charArrayWriter(){
        return ref_charArrayWriter;
    }
}
