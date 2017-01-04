package com.kingdangkou.weixin.weixiaodan.controller;

import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.service.FileService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dongy on 2016-11-24.
 */
@Controller
@RequestMapping("website/product/file")
public class FileController{
    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response){
        fileService.getFile(request, response);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Result result = fileService.saveFile(request);
        response.getWriter().print(JSONObject.fromObject(result));
    }

    public FileService getFileService() {
        return fileService;
    }

    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
}
