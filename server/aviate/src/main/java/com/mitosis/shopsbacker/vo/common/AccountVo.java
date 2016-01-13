/**
 * 
 */
package com.mitosis.shopsbacker.vo.common;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Anbukkani Gajendiran
 *
 */
@XmlRootElement
public class AccountVo {

	private String accountId;
	private String accountNo;
	private String secKey;
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getSecKey() {
		return secKey;
	}
	public void setSecKey(String secKey) {
		this.secKey = secKey;
	}
	
}
