package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.NotificationDao;
import com.kingdangkou.weixin.weixiaodan.entity.NotificationEntity;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.model.Success;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dongy on 2017-01-10.
 */
@Service
public class NotificationService {
    @Autowired
    private NotificationDao notificationDao;
    public Result save(String title) {
        notificationDao.save(new NotificationEntity(title));
        return new Success();
    }
}
