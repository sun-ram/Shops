package com.mitosis.shopsbacker.responsevo;

import java.util.List;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.inventory.ProductCategoryVo;

public class ProductCategoryResponseVo extends ResponseModel{
	
	public List<ProductCategoryVo> productCategoryVo;

	public List<ProductCategoryVo> getProductCategoryVo() {
		return productCategoryVo;
	}

	public void setProductCategoryVo(List<ProductCategoryVo> productCategoryVo) {
		this.productCategoryVo = productCategoryVo;
	}
	
	

}
