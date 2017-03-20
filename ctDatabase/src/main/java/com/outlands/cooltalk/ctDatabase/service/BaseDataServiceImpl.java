package com.outlands.cooltalk.ctDatabase.service;

import org.springframework.transaction.annotation.Transactional;

import com.outlands.cooltalk.ctDatabase.dao.BaseDao;

public abstract class BaseDataServiceImpl<T> implements BaseDataService<T> {
	
	protected abstract BaseDao<T> getDao();
	
	@Transactional(readOnly=true)
	@Override
	public T get(long id) {		
		return getDao().get(id);
	}

	@Transactional
	@Override
	public void save(T entity) {		
		getDao().save(entity);
	}
	
	@Transactional
	@Override
	public void update(T entity) {		
		getDao().update(entity);
	}
	
	@Transactional
	@Override
	public void delete(T entity) {		
		getDao().delete(entity);
	}

}
