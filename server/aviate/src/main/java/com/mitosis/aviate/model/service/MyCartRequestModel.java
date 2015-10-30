package com.mitosis.aviate.model.service;

import java.util.List;

import com.mitosis.aviate.model.MyCartModel;

public class MyCartRequestModel {

	private int customerId;
	private Long storeId;
	private List<MyCartModel> myCartList;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public List<MyCartModel> getMyCartList() {
		return myCartList;
	}

	public void setMyCartList(List<MyCartModel> myCartList) {
		this.myCartList = myCartList;
	}
}
