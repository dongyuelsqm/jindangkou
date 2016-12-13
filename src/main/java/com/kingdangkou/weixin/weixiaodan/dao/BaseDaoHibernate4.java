package com.kingdangkou.weixin.weixiaodan.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class BaseDaoHibernate4<T> implements BaseDao<T>
{
	protected SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	public SessionFactory getSessionFactory()
	{
		return this.sessionFactory;
	}
	public T get(Class<T> entityClazz , String value)
	{
		return get(entityClazz, value, "id");
	}

	public T get(Class<T> entityClazz, String value, String key){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "From " + entityClazz.getSimpleName() + " where " + key +" = " + value;
		T obj = session.createQuery(hql, entityClazz).uniqueResult();
		transaction.commit();
		return obj;
	}
	public void save(T entity)
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(entity);

		transaction.commit();
	}

	@Override
	public List<T> find(String hql) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		List orders = session.createQuery(hql).list();
		transaction.commit();
		return orders;
	}

	@Override
	public List<T> find(String name, String value, Class<T> cls) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		List list = session.createQuery("select from " + cls.getSimpleName() + " where " + name + " = " + value).list();
		transaction.commit();
		return list;
	}

	public List<T> find(String openID, String name, String value, Class<T> cls){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		List list = session.createQuery("select from " + cls.getSimpleName() + " where " + name + " = " + value + " and openID = " + openID).list();
		transaction.commit();
		return list;
	}

	@Override
	public void update(T entity){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
	}

	@Override
	public void update(String idName, String id, String field, String value, Class<T> cls) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "update " + cls.getSimpleName() + " set " + field + " = " + value + " where " + idName +" = " + id;
		session.createQuery(hql);
		transaction.commit();
	}

	@Override
	public void delete(T entity) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(entity);
		transaction.commit();
		session.close();
	}
}
