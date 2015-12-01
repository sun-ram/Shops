package com.mitosis.shopsbacker.util;

/**
 * Sales order status
 * 
 * @author fayaz
 *
 */
public enum OrderStatus {
	Placed, Shoper_Assigned, Picked, Packed, Backer_Assigned,  Backer_Started, Delivered;

	/**
	 * To check the given value is available or not in Order status
	 * @author Anbukkani
	 * @param string
	 * @return boolean
	 */
	public static boolean contains(String string) {

		for (OrderStatus c : OrderStatus.values()) {
			if (c.name().equals(string)) {
				return true;
			}
		}
		return false;
	}
}
