package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.NotificationDao;
import com.kingdangkou.weixin.weixiaodan.entity.NotificationEntity;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.model.Success;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Result delete(String id) {
        NotificationEntity entity = notificationDao.get(NotificationEntity.class, id);
        notificationDao.delete(entity);
        return new Success();
    }

    public Result list() {
        List<NotificationEntity> list = notificationDao.list(NotificationEntity.class);
        return new Result(true, list);
    }

    public Result getLatestNotification() {
        NotificationEntity latest = notificationDao.getLatest();
        return new Result(true, latest);
    }

    public Result update(String id, String title) {
        notificationDao.update(id, title);
        return new Success();
    }
}
