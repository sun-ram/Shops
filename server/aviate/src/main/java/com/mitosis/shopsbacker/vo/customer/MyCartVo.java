package com.mitosis.shopsbacker.vo.customer;

import com.mitosis.shopsbacker.vo.admin.StoreVo;
import com.mitosis.shopsbacker.vo.inventory.ProductVo;

public class MyCartVo {

	public String myCartId;
	
	public String productId;
	
	public String storeId;
	
	public String customerId;

	public CustomerVo customer;

	public ProductVo product;

	public StoreVo store;

	public int qty;
	
	

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

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

	public String getMyCartId() {
		return myCartId;
	}

	public void setMyCartId(String myCartId) {
		this.myCartId = myCartId;
	}

	public CustomerVo getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerVo customer) {
		this.customer = customer;
	}

	public ProductVo getProduct() {
		return product;
	}

	public void setProduct(ProductVo product) {
		this.product = product;
	}

	public StoreVo getStore() {
		return store;
	}

	public void setStore(StoreVo store) {
		this.store = store;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

}
