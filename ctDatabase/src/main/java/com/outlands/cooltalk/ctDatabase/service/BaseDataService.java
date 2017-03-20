package com.outlands.cooltalk.ctDatabase.service;

public interface BaseDataService<T> {

	public T get(long id);

	public void save(T entity);

	public void update(T entity);

	public void delete(T entity);

}
