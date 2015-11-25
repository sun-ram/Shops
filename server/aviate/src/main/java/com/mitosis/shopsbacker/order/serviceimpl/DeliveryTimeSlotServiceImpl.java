/**
 * 
 */
package com.mitosis.shopsbacker.order.serviceimpl;

import java.io.Serializable;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitosis.shopsbacker.model.DeliveryTimeSlot;
import com.mitosis.shopsbacker.order.dao.DeliveryTimeSlotDao;
import com.mitosis.shopsbacker.order.service.DeliveryTimeSlotService;

/**
 * @author Anbukkani Gajendiran
 *
 */
@Service("deliveryTimeSlotServiceImpl")
public class DeliveryTimeSlotServiceImpl implements DeliveryTimeSlotService<T>, Serializable {

	@Autowired 
	DeliveryTimeSlotDao deliveryTimeSlotDao;
	
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see com.mitosis.shopsbacker.order.service.DeliveryTimeSlotService#get(java.lang.String)
	 */
	@Override
	public DeliveryTimeSlot get(String id) {
 		return deliveryTimeSlotDao.getDeliveryTimeSlot(id);
	}

	/* (non-Javadoc)
	 * @see com.mitosis.shopsbacker.order.service.DeliveryTimeSlotService#update(com.mitosis.shopsbacker.model.DeliveryTimeSlot)
	 */
	@Override
	public void update(DeliveryTimeSlot deliveryTimeSlot) {
		   deliveryTimeSlotDao.updateDeliveryTimeSlot(deliveryTimeSlot);
		
	}

	/* (non-Javadoc)
	 * @see com.mitosis.shopsbacker.order.service.DeliveryTimeSlotService#save(com.mitosis.shopsbacker.model.DeliveryTimeSlot)
	 */
	@Override
	public void save(DeliveryTimeSlot deliveryTimeSlot) {
		  deliveryTimeSlotDao.saveDeliveryTimeSlot(deliveryTimeSlot);
	}

	/* (non-Javadoc)
	 * @see com.mitosis.shopsbacker.order.service.DeliveryTimeSlotService#delete(java.lang.String)
	 */
	@Override
	public void delete(String deliveryTimeSlotId) {
		  DeliveryTimeSlot deliveryTimeSlot = deliveryTimeSlotDao.getDeliveryTimeSlot(deliveryTimeSlotId);
		deliveryTimeSlotDao.deteteDeliveryTimeSlot(deliveryTimeSlot);
	}

}
