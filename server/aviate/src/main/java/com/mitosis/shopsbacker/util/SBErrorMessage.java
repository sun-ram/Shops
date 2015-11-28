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
	CATEGORY_ALREADY_HAS_PRODUCT_TYPE("E0006","This category already has a product"),
	PRODUCT_CATEGORY_ALREADY_EXITS("E0007","This product name already exists"),
	UOM_ALREDY_ASSIGNED_IN_PRODUCT("E0008","You can not able to delete this Unit Of Measurement. Because it is already assigned some product."),
	TAX_NAME_ALREADY_EXIST("E0009","Tax name already exist."),
	EMAILID_EXISTS("E0010","EmailId already registered."),
	SIGNUP_SUCCESS("E0011","Registered Successfully"),
	MOBILNO_EXISTS("E0012","Mobile Number already registered."),
	PROBLEM_IN_SENDING_EMAIL("E0013","Problem in sending email."),
	INVALID_TOKEN("E0014", "Invalid password reset token."),
	INVALID_PRODUCT_PRICE("E0015","Invalid Product Price."),
	INVALID_PRODUCT_UNIT("E0016","Invalid Product Unit."),
	PRODUCT_NOT_AVAILABLE("E0017","Product Not Available."),
	INVALID_USER_ACCOUNT("E0018","No user found with that username."),
	INVALID_CUSTOMER_ACCOUNT("E0019", "No customer found with that email address."), 
	AMOUNT_RANGE_ALREADY_EXIST("E0020", "Amount Range Already Exist."),
	INVALID_SHOPPER_ID("E0021", "Invalid shopper id.");

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
