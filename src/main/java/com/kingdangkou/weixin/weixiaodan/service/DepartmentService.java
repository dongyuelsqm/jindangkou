package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.DepartmentDao;
import com.kingdangkou.weixin.weixiaodan.entity.DepartmentEntity;
import com.kingdangkou.weixin.weixiaodan.model.ListResult;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.model.Success;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dongy on 2016-12-22.
 */
@Service
public class DepartmentService {
    @Autowired
    private DepartmentDao departmentDao;

    public Result add(String name) {
        departmentDao.save(new DepartmentEntity(name));
        return new Success();
    }

    public Result delete(String id) {
        DepartmentEntity departmentEntity = departmentDao.get(id);
        departmentDao.delete(departmentEntity);
        return new Success();
    }

    public ListResult list() {
        List<DepartmentEntity> list = departmentDao.list();
        return new ListResult(true, list);
    }
}
