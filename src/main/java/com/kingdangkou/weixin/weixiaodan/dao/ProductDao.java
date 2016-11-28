package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dongy on 2016-11-18.
 */
@Component
public class ProductDao extends BaseDaoHibernate4<Product>  {
    public List<Product> find(){
        return find("select * from Product");
    }
}
