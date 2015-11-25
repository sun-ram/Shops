/**
 * 
 */
package com.mitosis.shopsbacker.order.dao;

import com.mitosis.shopsbacker.model.DeliveryTimeSlot;
import com.mitosis.shopsbacker.model.OrderTax;

/**
 * @author Anbukkani Gajendiran
 *
 */
public interface DeliveryTimeSlotDao<T> {

	public void saveDeliveryTimeSlot(DeliveryTimeSlot deliveryTimeSlot);

	public void updateDeliveryTimeSlot(DeliveryTimeSlot deliveryTimeSlot);

	public void deteteDeliveryTimeSlot(DeliveryTimeSlot deliveryTimeSlot);

	public DeliveryTimeSlot getDeliveryTimeSlot(String id);
}
