package com.mitosis.shopsbacker.vo;

import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.shopsbacker.util.SBMessageStatus;

/**
 * @author prabakaran
 *
 * @param <T>
 * Reviewed by Sundaram  27/11/2015
 */
@XmlRootElement
public class ResponseModel {

	private String errorCode = "";
	private String errorString = "";
	private String status = SBMessageStatus.SUCCESS.getValue();

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorString() {
		return errorString;
	}

	public void setErrorString(String errorString) {
		this.errorString = errorString;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
