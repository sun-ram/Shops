package com.mitosis.shopsbacker.inventory.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.ProductOffer;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.vo.inventory.ProductOfferVo;
/**
 * @author RiyazKhan.M
 */
public interface ProductOfferService<T> {
	
	public void addProductOffer(ProductOffer productOffer);
	
	public void updateProductOffer(ProductOffer productOffer);
	
	public void deleteProductOffer(ProductOffer productOffer);
	
	public List<ProductOffer> getAllProductOffer();
	
	public ProductOffer getProductOffer(String id);
	
	public ProductOffer setProductOffer(ProductOfferVo productOfferVo, ProductOffer productOffer) throws Exception;
	
	public ProductOfferVo setProductOfferVo(ProductOffer productOffer) throws Exception;
	
	public List<ProductOffer> getProductOfferByMerchant(Merchant merchant);

	public List<ProductOffer> checkUniqueName(String params, Store store);
	
	public List<ProductOffer> getProductOfferByStore(Store store);

}
