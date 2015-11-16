package com.mitosis.shopsbacker.util;

public enum SBMessageProperties {

	CODE("errorCode"), MESSAGE("errorString"),STATUS("status");

	 String value;

	  private SBMessageProperties(String value) {
	    this.value = value;
	  }

	  public String getValue() {
	    return this.value;
	  }
	
}
