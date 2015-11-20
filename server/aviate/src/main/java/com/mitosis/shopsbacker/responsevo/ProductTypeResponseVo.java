package com.mitosis.shopsbacker.responsevo;

import java.util.List;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.inventory.ProductTypeVo;

public class ProductTypeResponseVo extends ResponseModel{

	public List<ProductTypeVo> productTypeVo;

	public List<ProductTypeVo> getProductTypeVo() {
		return productTypeVo;
	}

	public void setProductTypeVo(List<ProductTypeVo> productTypeVo) {
		this.productTypeVo = productTypeVo;
	}
	
	
}
