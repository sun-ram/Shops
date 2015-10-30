package com.mitosis.aviate.model.service;

import java.util.List;

import com.mitosis.aviate.model.MerchantDetails;

public class MerchantResponseModel extends ResponseModel {

	public List<MerchantDetails> merchantList;

	public List<MerchantDetails> getMerchantList() {
		return merchantList;
	}

	public void setMerchantList(List<MerchantDetails> merchantList) {
		this.merchantList = merchantList;
	}
}
