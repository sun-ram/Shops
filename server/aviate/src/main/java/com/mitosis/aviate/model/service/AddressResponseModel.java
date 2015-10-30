package com.mitosis.aviate.model.service;

import java.util.List;

import com.mitosis.aviate.model.Address;

public class AddressResponseModel extends ResponseModel{
	
	private List<Address> addressList;

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}
}
