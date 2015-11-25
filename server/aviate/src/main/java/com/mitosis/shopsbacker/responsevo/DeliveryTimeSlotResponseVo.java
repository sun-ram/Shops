/**
 * 
 */
package com.mitosis.shopsbacker.responsevo;

import java.util.ArrayList;
import java.util.List;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.order.DeliveryTimeSlotVo;

/**
 * @author Anbukkani Gajendiran
 *
 */
public class DeliveryTimeSlotResponseVo extends ResponseModel {

	List<DeliveryTimeSlotVo> deliveryTimeSlots =new ArrayList<DeliveryTimeSlotVo>();

	public List<DeliveryTimeSlotVo> getDeliveryTimeSlots() {
		return deliveryTimeSlots;
	}

	public void setDeliveryTimeSlot(List<DeliveryTimeSlotVo> deliveryTimeSlots) {
		this.deliveryTimeSlots = deliveryTimeSlots;
	}
	
	
}
