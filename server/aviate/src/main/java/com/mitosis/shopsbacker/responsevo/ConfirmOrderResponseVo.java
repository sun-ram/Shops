package com.mitosis.shopsbacker.responsevo;

import com.mitosis.shopsbacker.vo.ResponseModel;

public class ConfirmOrderResponseVo extends ResponseModel {

	public String orderNo;
	
	public String salesOrderId;

	public String getSalesOrderId() {
		return salesOrderId;
	}

	public void setSalesOrderId(String salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
}
