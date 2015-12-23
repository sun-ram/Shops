package com.mitosis.shopsbacker.inventory.dao;

import java.util.List;

import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.ProductOffer;
import com.mitosis.shopsbacker.model.Store;

/**
 * @author RiyazKhan.M
 */
public interface ProductOfferDao<T> {
	
	public void addProductOffer(ProductOffer productOffer);
	
	public void updateProductOffer(ProductOffer productOffer);
	
	public void deleteProductOffer(ProductOffer productOffer);
	
	public List<ProductOffer> getAllProductOffer();
	
	public ProductOffer getProductOffer(String id);
	
	public List<ProductOffer> getProductOfferByMerchant(Merchant merchant);
	
	public List<ProductOffer> getProductOfferByStore(Store store);

	public List<ProductOffer> checkUniqueName(String params, Store store);
}
