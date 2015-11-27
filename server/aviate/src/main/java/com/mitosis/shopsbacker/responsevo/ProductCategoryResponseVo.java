package com.mitosis.shopsbacker.responsevo;

import java.util.List;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.inventory.ProductCategoryVo;

public class ProductCategoryResponseVo extends ResponseModel{
	
	public List<ProductCategoryVo> productCategories;

	public List<ProductCategoryVo> getProductCategories() {
		return productCategories;
	}

	public void setProductCategories(List<ProductCategoryVo> productCategories) {
		this.productCategories = productCategories;
	}
	
	

}
