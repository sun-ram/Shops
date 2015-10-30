package com.mitosis.aviate.model.service;

import java.util.List;

import com.mitosis.aviate.model.MyCartModel;

public class MyCartResponseModel extends ResponseModel{
	
	private List<MyCartModel> myCartList;

	public List<MyCartModel> getMyCartList() {
		return myCartList;
	}

	public void setMyCartList(List<MyCartModel> myCartList) {
		this.myCartList = myCartList;
	}
}
