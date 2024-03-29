package com.kingdangkou.weixin.weixiaodan.listener;

import com.kingdangkou.weixin.weixiaodan.utils.FileHandler;
import com.kingdangkou.weixin.weixiaodan.utils.file.PathHandler;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;

/**
 * Created by dongy on 2017-01-18.
 */
public class InitListener implements ServletContextListener {

    private PathHandler pathHandler = new PathHandler();

    private FileHandler fileHandler = new FileHandler();

    private static final String upload = "upload";
    private static final String files = "files";
    private static final String temp = "temp";
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String base = pathHandler.getWebInfoPath();
        fileHandler.makeDir(base + File.separator + upload);
        fileHandler.makeDir(base + File.separator + files);
        fileHandler.makeDir(base + File.separator + temp);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
