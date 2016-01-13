/**
 * 
 */
package com.mitosis.shopsbacker.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Anbukkani Gajendiran
 *
 */
@Entity
@Table(name = "Account")
public class Account implements Serializable{
	private static final long serialVersionUID = 1L;
	private String accountId;
	private String accountNo;
	private String secKey;
	private char isactive;
	
	public Account(){
		
	}
	
	public Account( String accountId,String accountNo,String secKey,char isactive){
		this.accountId= accountId;
		this.accountNo=accountNo;
		this.secKey= secKey;
		this.isactive=isactive;
	}
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ACCOUNT_ID", unique = true, nullable = false, length = 32)
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	@Column(name = "ACCOUNT_NO", unique = true, nullable = false, length = 45)
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
	@Column(name = "SEC_KEY", unique = true, nullable = false, length = 64)
	public String getSecKey() {
		return secKey;
	}
	public void setSecKey(String secKey) {
		this.secKey = secKey;
	}
	
	@Column(name = "ISACTIVE", nullable = false, length = 1)
	public char getIsactive() {
		return isactive;
	}
	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}
}
