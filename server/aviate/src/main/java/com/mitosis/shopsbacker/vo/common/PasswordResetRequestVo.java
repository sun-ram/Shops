package com.mitosis.shopsbacker.vo.common;

import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.shopsbacker.vo.admin.UserVo;

/**
 * @author Kartheeswaran
 *
 * @param <T>
 * 
 *  Reviewed by Sundaram 27/11/2015
 */

@XmlRootElement
public class PasswordResetRequestVo {
	private String requestId;
	private String tokenId;
	private UserVo user;
	private String userType;
	private String passwordResetUrl;
	
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	
	public UserVo getUser() {
		return user;
	}
	public void setUser(UserVo user) {
		this.user = user;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getPasswordResetUrl() {
		return passwordResetUrl;
	}
	public void setPasswordResetUrl(String passwordResetUrl) {
		this.passwordResetUrl = passwordResetUrl;
	}
	
}