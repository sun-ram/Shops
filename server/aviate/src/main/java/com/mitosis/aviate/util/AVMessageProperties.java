package com.mitosis.aviate.util;

public enum AVMessageProperties {

	CODE("errorCode"), MESSAGE("errorString"),STATUS("status");

	 String value;

	  private AVMessageProperties(String value) {
	    this.value = value;
	  }

	  public String getValue() {
	    return this.value;
	  }
	
}
