package com.mitosis.shopsbacker.util;

/**
 * Enum contains all default error messages for Aviate.
 * 
 * @author Anbukkani G
 *
 */
public enum SBErrorMessage {
	
	METCHANT_NAME_ALREADY_EXIST("E0001","Merchant name already exist."),
	USER_NAME_ALREADY_EXIST("E0002","User name already exist."),
	INVALID_ADDRESS("E0003","Enter correct address");

	private final String erroeCode;
	  private final String errorMessage;
	  
	  private SBErrorMessage(String erroeCode,String errorMessage){
		  this.erroeCode=erroeCode;
		  this.errorMessage=errorMessage;
	  }
	  
	  public String getCode() {
		    return erroeCode;
		  }

		  public String getMessage() {
		    return errorMessage;
		  }
}
