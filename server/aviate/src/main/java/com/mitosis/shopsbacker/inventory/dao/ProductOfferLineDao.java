package com.mitosis.shopsbacker.inventory.dao;

import com.mitosis.shopsbacker.model.ProductOfferLine;

/**
 * @author RiyazKhan.M
 */
public interface ProductOfferLineDao<T> {
	
	public void addProductOfferLine(ProductOfferLine productOfferLine);
	
	public void updateProductOfferLine(ProductOfferLine productOfferLine);
	
	public void deleteProductOfferLine(ProductOfferLine productOfferLine);
	
	public ProductOfferLine getProductOfferLine(String id);
	
}
