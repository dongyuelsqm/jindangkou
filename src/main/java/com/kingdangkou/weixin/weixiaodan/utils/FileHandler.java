package com.kingdangkou.weixin.weixiaodan.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongy on 2016-11-22.
 */
@Component
public class FileHandler extends HttpServlet {
    String realPath;
    String tempPath;
    @Override
    public void init() throws ServletException {
        initFilePath();
        super.init();
    }

    private void initFilePath() {
        String basePath = getServletContext().getRealPath("/") + File.separator + "WEB-INF" + File.separator;
        realPath = basePath + "upload" + File.separator;
        tempPath = basePath + "temp" + File.separator;
        initPath(realPath);
        initPath(tempPath);
    }

    private void initPath(String path) {
        File tmpFile = new File(path);
        if (!tmpFile.exists()){
            tmpFile.mkdir();
        }
    }

    public ArrayList<String> saveFile(HttpServletRequest request) throws IOException, FileUploadException {
        File tmpFile = new File(tempPath);

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setSizeThreshold(1024 * 100);
        diskFileItemFactory.setRepository(tmpFile);

        ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
        upload.setHeaderEncoding("UTF-8");
        if (!ServletFileUpload.isMultipartContent(request)){
            return new ArrayList<>();
        }
        upload.setFileSizeMax(1024*1024);
        upload.setSizeMax(1024*1024 * 10);

        List<FileItem> fileItems = upload.parseRequest(request);
        ArrayList<String> fileNames = new ArrayList<String>();
        for (FileItem item: fileItems){
            String inputFullName = item.getName();
            if (inputFullName == null || inputFullName.trim().equals("")){
                continue;
            }
            String extandName = inputFullName.substring(inputFullName.lastIndexOf(".") + 1);
            InputStream stream = item.getInputStream();
            int fileName = RandomDataGenerator.generate();
            String storageName = String.valueOf(fileName) + extandName;
            FileOutputStream fileOutputStream = new FileOutputStream(realPath + "."+ storageName);
            byte buffer[] = new byte[1024];
            int len = 0;
            while ((len = stream.read(buffer)) >0){
                fileOutputStream.write(buffer, 0, len);
            }
            stream.close();
            fileOutputStream.close();
            item.delete();
            fileNames.add(storageName);
        }

        return fileNames;
    }

    private String getBaseFile(HttpServletRequest request) {
        return request.getSession().getServletContext().getRealPath("");
    }

    public void getFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String picName = request.getParameter("picName");
        String filePath = getBaseFile(request) + "WEB-INF"+ File.separator + "upload" + File.separator + picName;
        File file = new File(filePath);
        if (!file.exists()){
            request.setAttribute("message", "您要下载的资源已被删除！！");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }

        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(picName, "UTF-8"));
        FileInputStream in = new FileInputStream(filePath);
        OutputStream out = response.getOutputStream();
        byte buffer[] = new byte[1024];
        int len = 0;
        while ((len = in.read(buffer)) > 0){
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();


    }

    public void moveFile(ArrayList<String> files, String target){
        String realPath = "WEB-INF"+ File.separator + "files" + File.separator + target + File.separator;
        String originPath = "WEB-INF"+ File.separator + "upload" + File.separator;
        for (String fileName: files){
            File file = new File(originPath + fileName);
            file.renameTo(new File(realPath + fileName));
        }
    }
}
