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
	INVALID_ADDRESS("E0003","Enter correct address"),
	UOM_NAME_ALREADY_EXIST("E0004","Uom name already exist."),
	INVALID_USERNAME("E0003","Invalid username or password"),
	STORE_NAME_ALREADY_EXIST("E0005","Store name already exist."),
	UOM_ALREDY_ASSIGNED_IN_PRODUCT("E0008","You can not able to delete this Unit Of Measurement. Because it is already assigned some product."),
	TAX_NAME_ALREADY_EXIST("E0009","Tax name already exist.");

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
