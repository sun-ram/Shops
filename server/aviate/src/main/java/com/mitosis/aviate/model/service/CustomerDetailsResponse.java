package com.mitosis.aviate.model.service;

import java.util.List;

import com.mitosis.aviate.model.CustomerModel;

public class CustomerDetailsResponse extends ResponseModel{

	private List<CustomerModel> customerList;

	public List<CustomerModel> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<CustomerModel> customerList) {
		this.customerList = customerList;
	}
}
