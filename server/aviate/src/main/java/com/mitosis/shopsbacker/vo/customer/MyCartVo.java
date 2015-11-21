package com.mitosis.shopsbacker.vo.customer;

import com.mitosis.shopsbacker.vo.admin.StoreVo;
import com.mitosis.shopsbacker.vo.inventory.ProductVo;

public class MyCartVo {

	public String myCartId;

	public CustomerVo customer;

	public ProductVo product;

	public StoreVo store;

	public int qty;

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
