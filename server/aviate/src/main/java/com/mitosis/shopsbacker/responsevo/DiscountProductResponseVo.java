package com.mitosis.shopsbacker.responsevo;

/**
 * @author JAI BHARATHI.S
 */
import java.util.ArrayList;
import java.util.List;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.inventory.DiscountProductsVo;

public class DiscountProductResponseVo extends ResponseModel{
	
	public List<DiscountProductsVo> discountProductList = new ArrayList<DiscountProductsVo>();

	public List<DiscountProductsVo> getDiscountProductList() {
		return discountProductList;
	}

	public void setDiscountProductList(List<DiscountProductsVo> discountProductList) {
		this.discountProductList = discountProductList;
	}
	
}
