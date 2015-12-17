package com.mitosis.shopsbacker.inventory.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Discount;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.vo.inventory.DiscountVo;

/**
 * @author RiyazKhan.M
 */

public interface DiscountService<T> {
	
	public void addDiscount(Discount discount);
	
	public void updateDiscount(Discount discount);
	
	public void deleteDiscount(Discount discount);
	
	public List<Discount> getAllDiscountByMerchant(Merchant merchant);
	
	public List<Discount> getAllDiscountByStore(Store store);
	
	public Discount getDiscountById(String discountId);
	
	public Discount setDiscount(DiscountVo discountVo) throws Exception;
	
	public DiscountVo setDiscountVo(Discount discount) throws Exception;
	
	public List<Discount> getUniqeName(String name);

}
