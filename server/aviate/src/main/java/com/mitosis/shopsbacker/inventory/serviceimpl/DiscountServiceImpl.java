package com.mitosis.shopsbacker.inventory.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitosis.shopsbacker.inventory.dao.DiscountDao;
import com.mitosis.shopsbacker.inventory.service.DiscountService;
import com.mitosis.shopsbacker.model.Discount;

/**
 * @author RiyazKhan.M
 */
@Service("discountServiceImpl")
public class DiscountServiceImpl<T> implements DiscountService<T>, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	DiscountDao<T> discountDao;

	@Override
	public void addDiscount(Discount discount) {
		discountDao.addDiscount(discount);
		
	}

	@Override
	public void updateDiscount(Discount discount) {
		discountDao.updateDiscount(discount);
		
	}

	@Override
	public void deleteDiscount(Discount discount) {
		discountDao.deleteDiscount(discount);
		
	}

	@Override
	public List<Discount> getAllDiscount() {
		return discountDao.getAllDiscount();
	}

}
