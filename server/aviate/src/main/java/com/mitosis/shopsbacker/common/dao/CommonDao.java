package com.mitosis.shopsbacker.common.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface CommonDao<T>   {
	
	void save(T entity);

	void update(T entity);

	void delete(T entity);
	List<T> findById(int id); 

	List<T> findByName(String name); 
	List<T> findAll();
	List<T> findAll(DetachedCriteria criteria);
}
