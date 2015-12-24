package com.mitosis.shopsbacker.responsevo;

/**
 * @author JAI BHARATHI.S
 */
import java.util.ArrayList;
import java.util.List;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.inventory.ProductOfferLineVo;

public class ProductOfferLineResponseVo extends ResponseModel {
	
	List<ProductOfferLineVo> productOfferLineList = new ArrayList<ProductOfferLineVo>();

	public List<ProductOfferLineVo> getProductOfferLineList() {
		return productOfferLineList;
	}

	public void setProductOfferLineList(
			List<ProductOfferLineVo> productOfferLineList) {
		this.productOfferLineList = productOfferLineList;
	}
	
}
