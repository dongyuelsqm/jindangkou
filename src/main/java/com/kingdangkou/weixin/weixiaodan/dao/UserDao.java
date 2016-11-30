package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.User;
import org.springframework.stereotype.Component;

/**
 * Created by dongy on 2016-11-30.
 */
@Component
public class UserDao extends BaseDaoHibernate4<User> {
    public User get(String userName){
        return get(User.class, userName);
    }
}
