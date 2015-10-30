package com.mitosis.aviate.model.service;

import java.util.List;
import com.mitosis.aviate.model.CustomerProductList;

public class CustomerMyListResponse extends ResponseModel {
	
	private List<CustomerProductList> customerMyList;

	public List<CustomerProductList> getCustomerMyList() {
		return customerMyList;
	}

	public void setCustomerMyList(List<CustomerProductList> customerMyList) {
		this.customerMyList = customerMyList;
	}
	

}
