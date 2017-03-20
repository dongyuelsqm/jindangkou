package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.UserDao;
import com.kingdangkou.weixin.weixiaodan.entity.User;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.model.Success;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dongy on 2016-11-30.
 */
@Service
public class LoginService {

    @Autowired
    private UserDao userDao;
    public boolean check(String userName, String passWord){
        User user = userDao.get(userName);
        return user!=null && user.getPassword() !=null && user.getPassword().equals(passWord);
    }

    public Result update(String id, String code) {
        User user = userDao.get(User.class, id);
        user.setPassword(code);
        userDao.update(user);
        return new Success();
    }
}
