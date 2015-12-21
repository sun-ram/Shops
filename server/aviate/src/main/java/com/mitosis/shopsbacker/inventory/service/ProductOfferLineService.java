package com.mitosis.shopsbacker.inventory.service;

import java.util.List;

import com.mitosis.shopsbacker.model.ProductOffer;
import com.mitosis.shopsbacker.model.ProductOfferLine;
import com.mitosis.shopsbacker.vo.inventory.ProductOfferLineVo;

public interface ProductOfferLineService<T> {
	
	public void addProductOfferLine(ProductOfferLine productOfferLine);
	
	public void updateProductOfferLine(ProductOfferLine productOfferLine);
	
	public void deleteProductOfferLine(ProductOfferLine productOfferLine);
	
	public ProductOfferLine setProductOfferLine(ProductOfferLineVo productOfferLineVo, ProductOfferLine productOfferLine) throws Exception;
	
	public ProductOfferLineVo setProductOfferLineVo(ProductOfferLine productOfferLine) throws Exception;
	
	public ProductOfferLine getProductOfferLine(String id);
	
	public List<ProductOfferLine> getProductOfferLine(ProductOffer productOffer);

}
