package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.DepartmentEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dongy on 2016-12-22.
 */
@Component
public class DepartmentDao extends BaseDaoHibernate4<DepartmentEntity>{
    public DepartmentEntity get(String id){
        return get(DepartmentEntity.class, id);
    }

    public List<DepartmentEntity> list() {
        return list(DepartmentEntity.class);
    }
}
