package com.mitosis.aviate.model.service;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResponseModel {
	private String errorCode;
	private String errorString;
	private String status;
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
