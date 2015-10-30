package com.mitosis.aviate.model.service;

import java.util.List;

import com.mitosis.aviate.model.SalesOrderModel;

public class SalesOrderResponseModel extends ResponseModel {

	private List<SalesOrderModel> salesOrderModel;

	public List<SalesOrderModel> getSalesOrderModel() {
		return salesOrderModel;
	}

	public void setSalesOrderModel(List<SalesOrderModel> salesOrderModel) {
		this.salesOrderModel = salesOrderModel;
	}
}
