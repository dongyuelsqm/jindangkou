package com.kingdangkou.weixin.weixiaodan.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by dongy on 2016-11-22.
 */
@Component
public class FileHandler extends HttpServlet {
    public void saveFile(HttpServletRequest request) throws IOException, FileUploadException {
        String realPath = this.getServletContext().getRealPath("/WEB-INF/upload");
        String tmpPath = this.getServletContext().getRealPath("/WEB-INF/temp");
        File tmpFile = new File(tmpPath);
        if (!tmpFile.exists()){
            tmpFile.mkdir();
        }

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setSizeThreshold(1024 * 100);
        diskFileItemFactory.setRepository(tmpFile);

        ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
        upload.setHeaderEncoding("UTF-8");
        if (!ServletFileUpload.isMultipartContent(request)){
            return;
        }
        upload.setFileSizeMax(1024*1024);
        upload.setSizeMax(1024*1024 * 10);

        List<FileItem> fileItems = upload.parseRequest(request);
        for (FileItem item: fileItems){
            if (item.isFormField()){
                String name = item.getFieldName();
                String value = item.getString("UTF-8");

            }else {
                String inputFullName = item.getName();
                if (inputFullName == null || inputFullName.trim().equals("")){
                    continue;
                }
                String fileName = inputFullName.substring(inputFullName.lastIndexOf("\\") + 1);
                InputStream stream = item.getInputStream();
                FileOutputStream fileOutputStream = new FileOutputStream(realPath + fileName);
                byte buffer[] = new byte[1024];
                int len = 0;
                while ((len = stream.read(buffer)) >0){
                    fileOutputStream.write(buffer, 0, len);
                }
                stream.close();
                fileOutputStream.close();
                item.delete();
            }
        }
    }
}
