package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.utils.FileHandler;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by dongy on 2016-11-24.
 */
@Service
public class FileService {
    Logger logger = Logger.getLogger(FileService.class.getName());
    @Autowired
    private FileHandler fileHandler;

    public FileHandler getFileHandler() {
        return fileHandler;
    }

    public Result saveFile(HttpServletRequest request){
        try {
            ArrayList<String> files = fileHandler.saveFile(request);
            return new Result(true, JSONArray.fromObject(files).toString());
        } catch (Throwable e) {
            logger.warning(e.getMessage());
            return new Result(false, "");
        }
    }

    public void getFile(HttpServletRequest request, HttpServletResponse response){
        try {
            fileHandler.getFile(request, response);
        }catch (Throwable e) {
            logger.warning(e.getMessage());
        }
    }

    public void setFileHandler(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

}
