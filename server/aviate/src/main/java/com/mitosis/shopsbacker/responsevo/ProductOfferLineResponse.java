package com.mitosis.shopsbacker.responsevo;

import java.util.ArrayList;
import java.util.List;

import com.mitosis.shopsbacker.model.ProductOfferLine;
import com.mitosis.shopsbacker.vo.ResponseModel;

public class ProductOfferLineResponse extends ResponseModel{
		
	List<ProductOfferLine> productOfferLineList = new ArrayList<ProductOfferLine>();

	public List<ProductOfferLine> getProductOfferLineList() {
		return productOfferLineList;
	}

	public void setProductOfferLineList(List<ProductOfferLine> productOfferLineList) {
		this.productOfferLineList = productOfferLineList;
	}
	
}
