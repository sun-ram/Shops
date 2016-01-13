/**
 * 
 */
package com.mitosis.shopsbacker.common.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitosis.shopsbacker.common.dao.AccountDao;
import com.mitosis.shopsbacker.common.service.AccountService;
import com.mitosis.shopsbacker.model.Account;

/**
 * @author Anbukkani Gajendiran
 *
 */
@Service("addressServiceImpl")
public class AccountServiceImpl<T> implements AccountService<T>  {
	@Autowired
	AccountDao<T> accountDao;

	/* (non-Javadoc)
	 * @see com.mitosis.shopsbacker.common.service.AccountService#getAccountByNo(java.lang.String)
	 */
	@Override
	public Account getAccountByNo(String accountNo) {
		
		return accountDao.getAccountByNo(accountNo);
	}

	/* (non-Javadoc)
	 * @see com.mitosis.shopsbacker.common.service.AccountService#getAccountById(java.lang.String)
	 */
	@Override
	public Account getAccountById(String accountId) {
		return accountDao.getAccountById(accountId);
	}

}
