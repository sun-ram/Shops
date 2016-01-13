/**
 * 
 */
package com.mitosis.shopsbacker.common.service;

import com.mitosis.shopsbacker.model.Account;

/**
 * @author Anbukkani Gajendiran
 *
 */
public interface AccountService<T> {

	public Account getAccountByNo(String accountNo);

	public Account getAccountById(String accountId);
}
