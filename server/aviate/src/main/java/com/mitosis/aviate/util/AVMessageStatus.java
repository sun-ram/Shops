package com.mitosis.aviate.util;

/**
 * @author Anbukkani G
 *
 */
public enum AVMessageStatus {

	FAILURE("FAILURE"), SUCCESS("SUCCESS");

	 String value;

	  private AVMessageStatus(String value) {
	    this.value = value;
	  }

	  public String getValue() {
	    return this.value;
	  }
	
}
