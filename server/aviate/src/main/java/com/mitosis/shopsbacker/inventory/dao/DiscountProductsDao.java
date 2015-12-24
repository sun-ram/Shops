package com.mitosis.shopsbacker.inventory.dao;

import java.util.List;

import com.mitosis.shopsbacker.model.Discount;
import com.mitosis.shopsbacker.model.DiscountProduct;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Store;

/**
 * @author RiyazKhan.M
 */

public interface DiscountProductsDao<T> {
	
	public void addDiscountProduct(DiscountProduct discountProduct);
	
	public void updateDiscountProduct(DiscountProduct discountProduct);
	
	public void deleteDiscountProduct(DiscountProduct discountProduct);
	
	public DiscountProduct getDiscountProductById(String discountProductId);
	
	public List<DiscountProduct> getDiscountProductByMerchant(Merchant merchant);
	
	public List<DiscountProduct> getDiscountProductByStore(Store store);
	
	public List<DiscountProduct> getDiscountProductByDiscount(Discount discount);


}
