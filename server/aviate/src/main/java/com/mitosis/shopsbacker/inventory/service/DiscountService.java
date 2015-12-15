package com.mitosis.shopsbacker.inventory.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Discount;

/**
 * @author RiyazKhan.M
 */

public interface DiscountService<T> {
	
	public void addDiscount(Discount discount);
	
	public void updateDiscount(Discount discount);
	
	public void deleteDiscount(Discount discount);
	
	public List<Discount> getAllDiscount();


}
