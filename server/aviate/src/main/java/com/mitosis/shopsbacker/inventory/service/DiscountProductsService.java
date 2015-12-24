package com.mitosis.shopsbacker.inventory.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Discount;
import com.mitosis.shopsbacker.model.DiscountProduct;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.vo.inventory.DiscountProductsVo;

/**
 * @author RiyazKhan.M
 */

public interface DiscountProductsService<T> {
	
	public void addDiscountProduct(DiscountProduct discountProduct);
	
	public void updateDiscountProduct(DiscountProduct discountProduct);
	
	public void deleteDiscountProduct(DiscountProduct discountProduct);
	
	public DiscountProduct getDiscountProductById(String discountProductId);
	
	public List<DiscountProduct> getDiscountProductByMerchant(Merchant merchant);
	
	public List<DiscountProduct> getDiscountProductByStore(Store store);
	
	public DiscountProduct setDiscountProduct(DiscountProductsVo discountProductVo)throws Exception;
	
	public DiscountProductsVo setDiscountVo(DiscountProduct discountProduct)throws Exception;

	public List<DiscountProduct> getDiscountProductByDiscount(Discount discount);
}
