/**
 * 
 */
package com.mitosis.shopsbacker.util;

/**
 * @author Anbukkani Gajendiran
 *
 */
public enum TransactionStatus {



	SUCCESS("Success"),
	FAILURE("Failure)"),
	PROCESSING("Processing");
	
	 private final String transactionStatus;
	  
	  private TransactionStatus(String transactionStatus){
		  this.transactionStatus=transactionStatus;
	  }
	  
	  public String getValue() {
		    return transactionStatus;
		  }

}
