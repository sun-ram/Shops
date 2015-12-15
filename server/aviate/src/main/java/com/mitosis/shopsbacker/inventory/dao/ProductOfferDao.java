package com.mitosis.shopsbacker.inventory.dao;

import java.util.List;

import com.mitosis.shopsbacker.model.ProductOffer;

/**
 * @author RiyazKhan.M
 */
public interface ProductOfferDao<T> {
	
	public void addProductOffer(ProductOffer productOffer);
	
	public void updateProductOffer(ProductOffer productOffer);
	
	public void deleteProductOffer(ProductOffer productOffer);
	
	public List<ProductOffer> getAllProductOffer();

}