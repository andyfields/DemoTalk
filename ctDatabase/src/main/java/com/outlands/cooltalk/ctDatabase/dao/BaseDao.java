package com.outlands.cooltalk.ctDatabase.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDao<T> {
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	@SuppressWarnings("rawtypes")
	protected Class cls;
	
	@SuppressWarnings("rawtypes")
	protected BaseDao(Class cls) {
		this.cls = cls;
	}
	
	@SuppressWarnings("unchecked")
	public T get(long id) {
		Session session = getCurrentSession();
        T entity = (T) session.get(cls, id);
        return entity; 
	}
		
	public void save(T entity) {
		Session session = getCurrentSession();
        session.save(entity);
	}
		
	public void update(T entity) {
		Session session = getCurrentSession();
        session.update(entity);
	}
		
	public void saveOrUpdate(T entity) {
		Session session = getCurrentSession();
        session.saveOrUpdate(entity);
	}
		
	public void delete(T entity) {
		Session session = getCurrentSession();
        session.delete(entity);
	}
	
	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
		
}
