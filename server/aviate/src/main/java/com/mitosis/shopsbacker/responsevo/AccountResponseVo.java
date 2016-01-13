/**
 * 
 */
package com.mitosis.shopsbacker.responsevo;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.common.AccountVo;

/**
 * @author Anbukkani Gajendiran
 *
 */
public class AccountResponseVo extends ResponseModel {

	private AccountVo account;

	public AccountVo getAccount() {
		return account;
	}

	public void setAccount(AccountVo account) {
		this.account = account;
	}
}
