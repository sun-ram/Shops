package com.mitosis.shopsbacker.util;

/**
 * @author Anbukkani G
 *
 */
public enum SBMessageStatus {

	FAILURE("FAILURE"), SUCCESS("SUCCESS");

	 String value;

	  private SBMessageStatus(String value) {
	    this.value = value;
	  }

	  public String getValue() {
	    return this.value;
	  }
	
}
