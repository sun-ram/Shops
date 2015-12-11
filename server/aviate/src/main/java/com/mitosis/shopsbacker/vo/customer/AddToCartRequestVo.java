package com.mitosis.shopsbacker.vo.customer;

import java.util.List;

public class AddToCartRequestVo {
	
	private String storeId;
	
	private String customerId;
	
	private List<MyCartProductVo> products;

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public List<MyCartProductVo> getProducts() {
		return products;
	}

	public void setProducts(List<MyCartProductVo> products) {
		this.products = products;
	}
	
}
