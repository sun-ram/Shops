/**
 * 
 */
package com.mitosis.shopsbacker.util;

/**
 * 
 * @author Anbukkani Gajendiran
 *
 */
public enum TransactionType {

	SALES_ORDER("Sales Order"),
	SALES_ORDER_RETURN("Sales Order Return(refund)"),
	BILLING_FOR_COD("Billing for COD");
	
	 private final String transactionType;
	  
	  private TransactionType(String transactionType){
		  this.transactionType=transactionType;
	  }
	  
	  public String getValue() {
		    return transactionType;
		  }
}
