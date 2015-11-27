package com.mitosis.shopsbacker.common.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.common.dao.CommonDao;
import com.mitosis.shopsbacker.model.PasswordResetRequest;

/**
 * @author prabakaran
 *
 * @param <T>
 * 
 * Reviewed by Sundaram 27/11/2015
 */
@Repository
public class CommonDaoImpl<T> extends CustomHibernateDaoSupport<T> implements CommonDao<T>, Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2285756145912074270L;

	@SuppressWarnings("unchecked")
	@Override
	public PasswordResetRequest savePasswordResetRequest(PasswordResetRequest passwordResetRequest) {
		try{
			save((T) passwordResetRequest);
			return passwordResetRequest;
		}catch(Exception e){
			throw e;
		}
	}


	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<T> findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAll(DetachedCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public PasswordResetRequest getPasswordResetRequestByTokenId(String tokenId) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(PasswordResetRequest.class);
			criteria.add(Restrictions.eq("tokenId", tokenId));
			return (PasswordResetRequest) findUnique(criteria);
		} catch (Throwable e) {
			e.printStackTrace();
			throw (e);
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public boolean deletePasswordResetRequest(
			PasswordResetRequest passwordResetRequest) {
		boolean result = false;
		try{
			delete((T) passwordResetRequest);
			result = true;
		}catch(Exception e){
			throw e;
		}
		return result;
	}
}
