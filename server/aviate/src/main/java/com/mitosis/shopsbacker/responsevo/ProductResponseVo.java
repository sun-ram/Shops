package com.mitosis.shopsbacker.responsevo;

import java.util.List;

import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.inventory.ProductVo;


public class ProductResponseVo extends ResponseModel{
	
	public List<ProductVo> productList;
	
	public ProductVo product;

	public List<ProductVo> getProduct() {
		return productList;
	}

	public void setProduct(List<ProductVo> product) {
		this.productList = product;
	}

	public void setProduct(ProductVo product) {
		this.product = product;
	}
	

	

}
