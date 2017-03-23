package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.UserDao;
import com.kingdangkou.weixin.weixiaodan.entity.User;
import com.kingdangkou.weixin.weixiaodan.model.Failure;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.model.Success;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * Created by dongy on 2016-11-30.
 */
@Service
public class LoginService {

    @Autowired
    private UserDao userDao;

    public User check(String userName, String passWord){
        User user = userDao.get(userName, passWord);
        if (!isPassed(user, userName, passWord)){
            return null;
        }
        return user;
    }

    boolean isPassed(User user, String name, String passWord) {
        if (user == null || user.getUserName() == null || user.getPassword() == null) return false;
        return user.getUserName().equals(name) && user.getPassword().equals(passWord);
    }

    public String loginout() {
        //清空session
        this.getSession().invalidate();
        return "";
    }

    public HttpSession getSession() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
    }


    public Result update(String id, String oldCode, String newCode, String confirmCode) {
        if (!newCode.equals(confirmCode)) return new Failure("new Code not equal confirmCode");
        User user = userDao.get(User.class, id);
        if (!user.getPassword().equals(oldCode)) return new Failure("old code not equal origin code");
        user.setPassword(newCode);
        userDao.update(user);
        return new Success();
    }
}
