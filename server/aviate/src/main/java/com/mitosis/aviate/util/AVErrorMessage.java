package com.mitosis.aviate.util;

/**
 * Enum contains all default error messages for Aviate.
 * 
 * @author Anbukkani G
 *
 */
public enum AVErrorMessage {
	
	MANDATORY_FIELDS_MISSING("E0001","Mandatory Fields missing, please provide values for mandatory fields"),
	CUSTOMER_ALREADY_EXITS("E0002","Customer already exists"),
	INVALID_ACCOUNT("E0004","Invalid account"),
	PRODUCT_EXIST_ERROR("E0007","Customer added product is already exists in list"),
	PRODUCT_CATEGORY_ALREADY_EXITS("E0009","This category already exists"),
	ADD_CART_ERORR("E0014","Unable to add or update product to my cart due to technical issue"),
	CART_PRODUCT_DELETE_ERROR("E0015","Unable to delete product from my cart"),
	CATEGORY_ALREADY_HAS_PRODUCT_TYPE("E0017","This category already has product type"),
	PRODUCT_NOT_AVAILABLE("E0018","Products are not available"),
	STORAGEBIN_NOT_FOUND("E0019","StorageBin not found"),
	STORE_NAME_EMPTY_ERROR("E0020","Store Name should not empty"),
	INVENTORY_ALREADY_EXIST_ERROR("E0021","Inventory Already available for this store and warehouse"),
	WAREHOUSE_NOT_AVAILABLE("E0022","Warehouse not available"),
	NO_PRODUCT_CATEGORY("E0023","There is no product categories available"),
	ADDRESS_NOT_VALID("E0024","Please enter correct address,City,State,Country and PostalCode"),
	EMAILID_ALREADY_EXIT("E0025","Email-id already registered"),
	USER_ALREADY_EXIT("E0026","Username(Email-id) Already exists"),
	INVALID_LOGIN("E0027","Invalid Email/Password"),
	ADDRESS_LENGTH_ERROR("E0028","Address is not valid,Max 200 Characters."),
	PRODUCT_DELETE_ERROR("E0029","Can't Delete, This product in sales.");
	

	private final String erroeCode;
	  private final String errorMessage;
	  
	  private AVErrorMessage(String erroeCode,String errorMessage){
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
