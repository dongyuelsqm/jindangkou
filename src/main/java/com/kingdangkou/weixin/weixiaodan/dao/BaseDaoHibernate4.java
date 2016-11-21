package com.kingdangkou.weixin.weixiaodan.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BaseDaoHibernate4<T> implements BaseDao<T>
{
	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	public SessionFactory getSessionFactory()
	{
		return this.sessionFactory;
	}
	public T get(Class<T> entityClazz , String key)
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "From " + entityClazz.getSimpleName() +" where id = " + key;
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
}
