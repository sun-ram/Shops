package com.mitosis.shopsbacker.responsevo;

import java.util.ArrayList;
import java.util.List;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.inventory.ProductStockVo;

public class ProductStockResponseVo extends ResponseModel{
	
	List<ProductStockVo> productListVo = new ArrayList<ProductStockVo>();

	public List<ProductStockVo> getProductListVo() {
		return productListVo;
	}

	public void setProductListVo(List<ProductStockVo> productListVo) {
		this.productListVo = productListVo;
	}
	
	
}
