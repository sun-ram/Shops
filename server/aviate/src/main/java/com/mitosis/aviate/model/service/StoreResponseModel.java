package com.mitosis.aviate.model.service;

import java.util.List;

import com.mitosis.aviate.model.StoreModel;

public class StoreResponseModel extends ResponseModel{
	
	public List<StoreModel> storeList;

	public List<StoreModel> getStoreList() {
		return storeList;
	}

	public void setStoreList(List<StoreModel> storeList) {
		this.storeList = storeList;
	}

}
