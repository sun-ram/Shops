package com.mitosis.aviate.model.service;

import java.util.List;

import com.mitosis.aviate.model.BinProductModel;

public class BinProductListResponse {
	
	public List<BinProductModel> binProductList;

	public List<BinProductModel> getBinProductList() {
		return binProductList;
	}

	public void setBinProductList(List<BinProductModel> binProductList) {
		this.binProductList = binProductList;
	}
}
