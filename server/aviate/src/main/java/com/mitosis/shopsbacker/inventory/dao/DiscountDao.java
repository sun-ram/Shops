package com.mitosis.shopsbacker.inventory.dao;

import java.util.List;

import com.mitosis.shopsbacker.model.Discount;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Store;
/**
 * @author RiyazKhan.M
 */
public interface DiscountDao<T> {
	
	public void addDiscount(Discount discount);
	
	public void updateDiscount(Discount discount);
	
	public void deleteDiscount(Discount discount);
	
	public List<Discount> getAllDiscountByMerchant(Merchant merchant);
	
	public List<Discount> getAllDiscountByStore(Store store);
	
	public Discount getDiscountById(String discountId);
	
	public List<Discount> getUniqeName(Store store,String name);

}
