package com.mitosis.shopsbacker.inventory.service;

import com.mitosis.shopsbacker.model.DiscountProduct;

/**
 * @author RiyazKhan.M
 */

public interface DiscountProductsService<T> {
	
	public void addDiscountProduct(DiscountProduct discountProduct);
	
	public void updateDiscountProduct(DiscountProduct discountProduct);
	
	public void deleteDiscountProduct(DiscountProduct discountProduct);
	
	public DiscountProduct getDiscountProductById(String discountProductId);
}
