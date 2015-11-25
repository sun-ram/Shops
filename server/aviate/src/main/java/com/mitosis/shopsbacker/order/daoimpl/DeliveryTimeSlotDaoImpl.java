/**
 * 
 */
package com.mitosis.shopsbacker.order.daoimpl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.model.DeliveryTimeSlot;
import com.mitosis.shopsbacker.order.dao.DeliveryTimeSlotDao;

/**
 * @author Anbukkani Gajendiran
 *
 */
@Repository
public class DeliveryTimeSlotDaoImpl<T> extends CustomHibernateDaoSupport<T>
		implements DeliveryTimeSlotDao<T>, Serializable {
 
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see com.mitosis.shopsbacker.order.dao.DeliveryTimeSlotDao#saveDeliveryTimeSlot(com.mitosis.shopsbacker.model.DeliveryTimeSlot)
	 */
	@Override
	public void saveDeliveryTimeSlot(DeliveryTimeSlot deliveryTimeSlot) {
		save((T)deliveryTimeSlot);
		
	}

	/* (non-Javadoc)
	 * @see com.mitosis.shopsbacker.order.dao.DeliveryTimeSlotDao#updateDeliveryTimeSlot(com.mitosis.shopsbacker.model.DeliveryTimeSlot)
	 */
	@Override
	public void updateDeliveryTimeSlot(DeliveryTimeSlot deliveryTimeSlot) {
		update((T)deliveryTimeSlot);
		
	}

	/* (non-Javadoc)
	 * @see com.mitosis.shopsbacker.order.dao.DeliveryTimeSlotDao#deteteDeliveryTimeSlot(com.mitosis.shopsbacker.model.DeliveryTimeSlot)
	 */
	@Override
	public void deteteDeliveryTimeSlot(DeliveryTimeSlot deliveryTimeSlot) {
		 delete((T)deliveryTimeSlot);
		
	}

	/* (non-Javadoc)
	 * @see com.mitosis.shopsbacker.order.dao.DeliveryTimeSlotDao#getDeliveryTimeSlot(java.lang.String)
	 */
	@Override
	public DeliveryTimeSlot getDeliveryTimeSlot(String id) {
		return (DeliveryTimeSlot)getSession().get(DeliveryTimeSlot.class, id);
	}
	
	

}
