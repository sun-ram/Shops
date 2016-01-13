/**
 * 
 */
package com.mitosis.shopsbacker.common.dao;

import com.mitosis.shopsbacker.model.Account;

/**
 * @author Anbukkani Gajendiran
 *
 */
public interface AccountDao<T> {

	public Account getAccountByNo(String accountNo);
	
	public Account getAccountById(String accountId);
}
