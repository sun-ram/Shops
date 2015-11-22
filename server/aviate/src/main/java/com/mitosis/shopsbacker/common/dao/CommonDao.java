package com.mitosis.shopsbacker.common.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.mitosis.shopsbacker.model.PasswordResetRequest;

public interface CommonDao<T>   {
	
	public PasswordResetRequest savePasswordResetRequest(PasswordResetRequest passwordResetRequest);
	
	public PasswordResetRequest getPasswordResetRequestByTokenId(String tokenId);
	
	public boolean deletePasswordResetRequest(PasswordResetRequest passwordResetRequest);
	
	void save(T entity);

	void update(T entity);

	void delete(T entity);
	List<T> findById(int id); 

	List<T> findByName(String name); 
	List<T> findAll();
	List<T> findAll(DetachedCriteria criteria);
}
