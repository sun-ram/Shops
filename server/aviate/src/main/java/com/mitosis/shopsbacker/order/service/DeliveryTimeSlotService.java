/**
 * 
 */
package com.mitosis.shopsbacker.order.service;

import com.mitosis.shopsbacker.model.DeliveryTimeSlot;

/**
 * @author Anbukkani Gajendiran
 *
 */
public interface DeliveryTimeSlotService<T> {

	public DeliveryTimeSlot get(String id);
	
	public void update(DeliveryTimeSlot deliveryTimeSlot);
	
	public void save(DeliveryTimeSlot deliveryTimeSlot);
	
	public void delete(String deliveryTimeSlotId);
	
}
