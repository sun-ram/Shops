package com.mitosis.shopsbacker.inventory.serviceimpl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitosis.shopsbacker.inventory.dao.DiscountProductsDao;
import com.mitosis.shopsbacker.inventory.service.DiscountProductsService;
import com.mitosis.shopsbacker.model.DiscountProduct;

/**
 * @author RiyazKhan.M
 */
@Service("discountProductsServiceImpl")
public class DiscountProductsServiceImpl<T> implements DiscountProductsService<T>, Serializable {


	private static final long serialVersionUID = 1L;
	
	@Autowired
	DiscountProductsDao<T> discountProductsDao;

	@Override
	public void addDiscountProduct(DiscountProduct discountProduct) {
		discountProductsDao.addDiscountProduct(discountProduct);
		
	}

	@Override
	public void updateDiscountProduct(DiscountProduct discountProduct) {
		discountProductsDao.updateDiscountProduct(discountProduct);
		
	}

	@Override
	public void deleteDiscountProduct(DiscountProduct discountProduct) {
		discountProductsDao.deleteDiscountProduct(discountProduct);
		
	}

	@Override
	public DiscountProduct getDiscountProductById(String discountProductId) {
		return discountProductsDao.getDiscountProductById(discountProductId);
	}

}
