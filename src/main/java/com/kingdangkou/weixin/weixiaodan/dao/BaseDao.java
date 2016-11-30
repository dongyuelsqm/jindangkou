package com.kingdangkou.weixin.weixiaodan.dao;

import java.util.List;

/**
 * Created by dongy on 2016-11-18.
 */
public interface BaseDao<T> {
    public T get(Class<T> entityClazz , String key);
    public void save(T obj);
    public List<T> find(String key);
    public List<T> find(String name, String value, Class<T> cls);
    public void update(T obj);

    public void update(String idName, String id, String field, String value, Class<T> cls);

    public void delete(T entity);
}
