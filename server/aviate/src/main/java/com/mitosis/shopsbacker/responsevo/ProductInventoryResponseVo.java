package com.mitosis.shopsbacker.responsevo;

import java.util.ArrayList;
import java.util.List;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.inventory.ProductInventoryVo;

public class ProductInventoryResponseVo extends ResponseModel{

	List<ProductInventoryVo> productinventory = new ArrayList<ProductInventoryVo>();

	public List<ProductInventoryVo> getProductinventory() {
		return productinventory;
	}

	public void setProductinventory(List<ProductInventoryVo> productinventory) {
		this.productinventory = productinventory;
	}

}
