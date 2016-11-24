package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.utils.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    public void saveFile(HttpServletRequest request){
        try {
            fileHandler.saveFile(request);
        } catch (Throwable e) {
            logger.warning(e.getMessage());
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
