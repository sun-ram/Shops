package com.mitosis.shopsbacker.order.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.ShippingCharges;
import com.mitosis.shopsbacker.order.dao.ShippingChargesDao;
import com.mitosis.shopsbacker.order.service.ShippingChargesService;

/**
 * @author RiyazKhan.M
 */
@Service("shippingChargesServiceImpl")
public class ShippingChargesServiceImpl<T> implements
ShippingChargesService<T>, Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	ShippingChargesDao<T> shippingChargesDao;

	@Override
	@Transactional
	public List<ShippingCharges> getShippingCharges(Merchant merchant) {
		return shippingChargesDao.getShippingCharges(merchant);
	}

	@Override
	@Transactional
	public void saveShippingCharges(ShippingCharges shippingCharges) {
		shippingChargesDao.saveShippingCharges(shippingCharges);

	}

	@Override
	@Transactional
	public void updateShippingCharges(ShippingCharges shippingCharges) {
		shippingChargesDao.updateShippingCharges(shippingCharges);

	}

	@Override
	@Transactional
	public ShippingCharges getShippingChargesById(String id) {
		return shippingChargesDao.getShippingChargesById(id);
	}

	@Override
	@Transactional
	public void deleteShippingCharges(String id) {
		shippingChargesDao.deleteShippingCharges(id);
	}

}
