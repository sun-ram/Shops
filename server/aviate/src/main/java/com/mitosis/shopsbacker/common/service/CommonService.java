package com.mitosis.shopsbacker.common.service;

import com.mitosis.shopsbacker.model.PasswordResetRequest;

public interface CommonService <T> {

	void save (T entity);
	
	void update(T entity);

	void delete(T entity);
	
	public PasswordResetRequest savePasswordResetRequest(String userId, String userType);
	
	public PasswordResetRequest getPasswordResetRequestByTokenId(String tokenId);
	
	public boolean deletePasswordResetRequest(PasswordResetRequest passwordResetRequest);
	
}
