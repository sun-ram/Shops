package com.mitosis.shopsbacker.common.serviceimpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.common.dao.CommonDao;
import com.mitosis.shopsbacker.common.service.CommonService;
import com.mitosis.shopsbacker.model.PasswordResetRequest;
import com.mitosis.shopsbacker.util.CommonUtil;

@Service("commonServiceImpl")
public class CommonServiceImpl<T> implements CommonService<T> {

	@Autowired
	CommonDao<T> commonDao;

	@Override
	public void save(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public PasswordResetRequest savePasswordResetRequest(String userId, String userType) {
		PasswordResetRequest pwdResetReq = new PasswordResetRequest();
		try {
			pwdResetReq = (PasswordResetRequest) CommonUtil.setAuditColumnInfo(PasswordResetRequest.class.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		pwdResetReq.setUserId(userId);
		pwdResetReq.setUserType(userType);
		pwdResetReq.setTokenId(UUID.randomUUID().toString().replace("-", ""));
		pwdResetReq.setIsactive('Y');
		commonDao.savePasswordResetRequest(pwdResetReq);
		return pwdResetReq;
	}

	@Override
	public PasswordResetRequest getPasswordResetRequestByTokenId(String tokenId) {
		return commonDao.getPasswordResetRequestByTokenId(tokenId);
	}

	@Override
	public boolean deletePasswordResetRequest(
			PasswordResetRequest passwordResetRequest) {
		boolean result = commonDao.deletePasswordResetRequest(passwordResetRequest);
		return result;
	}

	
}
