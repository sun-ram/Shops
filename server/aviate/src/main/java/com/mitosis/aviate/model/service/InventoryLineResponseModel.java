package com.mitosis.aviate.model.service;

import java.util.List;

import com.mitosis.aviate.model.ProductDetails;
import com.mitosis.aviate.model.StorageBinModel;

public class InventoryLineResponseModel extends ResponseModel {
	
	public List<ProductDetails> productList;
	public List<StorageBinModel> storageBinList;
	
	public List<ProductDetails> getProductList() {
		return productList;
	}
	public void setProductList(List<ProductDetails> productList) {
		this.productList = productList;
	}
	public List<StorageBinModel> getStorageBinList() {
		return storageBinList;
	}
	public void setStorageBinList(List<StorageBinModel> storageBinList) {
		this.storageBinList = storageBinList;
	}
	
}
