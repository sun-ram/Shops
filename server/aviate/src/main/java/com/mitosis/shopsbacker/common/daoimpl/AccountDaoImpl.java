/**
 * 
 */
package com.mitosis.shopsbacker.common.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.dao.AccountDao;
import com.mitosis.shopsbacker.model.Account;

/**
 * @author Anbukkani Gajendiran
 *
 */
@Repository
public class AccountDaoImpl<T> extends CustomHibernateDaoSupport<T> implements
		AccountDao<T>, Serializable {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mitosis.shopsbacker.common.dao.AccountDao#getAccountByNo(java.lang
	 * .String)
	 */
	@Override
	public Account getAccountByNo(String accountNo) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Account.class);
		criteria.add(Restrictions.eq("accountNo", accountNo));
		List<Account> accounts = (List<Account>) findAll(criteria);
		Account account = null;
		if(!accounts.isEmpty()){
			  account = accounts.get(0);
		}
		return account;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mitosis.shopsbacker.common.dao.AccountDao#getAccountById(java.lang
	 * .String)
	 */
	@Override
	public Account getAccountById(String accountId) {
		return null;
	}

}
