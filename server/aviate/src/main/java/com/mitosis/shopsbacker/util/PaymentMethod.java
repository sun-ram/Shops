package com.mitosis.shopsbacker.util;

/**
 * Sales order payment method
 * 
 * @author JAI
 *
 */
public enum PaymentMethod {
	COD, WT;

	/**
	 * To check the given value is available or not in Payment method
	 * @author Anbukkani
	 * @param string
	 * @return boolean
	 */
	public static boolean contains(String string) {

		for (PaymentMethod c : PaymentMethod.values()) {
			if (c.name().equals(string)) {
				return true;
			}
		}
		return false;
	}
}
