package com.mitosis.shopsbacker.service;

public interface CommonService <T> {

	void save (T entity);
	
	void update(T entity);

	void delete(T entity);
	
}
