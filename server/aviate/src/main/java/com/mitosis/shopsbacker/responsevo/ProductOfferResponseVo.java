package com.mitosis.shopsbacker.responsevo;

import java.util.ArrayList;
import java.util.List;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.inventory.ProductOfferVo;

public class ProductOfferResponseVo extends ResponseModel{
	
	List<ProductOfferVo> productOfferList = new ArrayList<ProductOfferVo>();

	public List<ProductOfferVo> getProductOfferList() {
		return productOfferList;
	}

	public void setProductOfferList(List<ProductOfferVo> productOfferList) {
		this.productOfferList = productOfferList;
	}
	
}
