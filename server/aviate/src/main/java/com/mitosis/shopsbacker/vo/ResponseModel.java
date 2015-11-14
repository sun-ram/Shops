package com.mitosis.shopsbacker.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.shopsbacker.util.SBMessageStatus;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
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
