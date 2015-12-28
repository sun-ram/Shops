package com.mitosis.shopsbacker.responsevo;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.order.TransactionDetailVo;

public class ConfirmOrderResponseVo extends ResponseModel {

	public String orderNo;
	
	public String salesOrderId;
	
	public String paymentMethod;
	
	public TransactionDetailVo transactionDatas;

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

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

	public TransactionDetailVo getTransactionDatas() {
		return transactionDatas;
	}

	public void setTransactionDatas(TransactionDetailVo transactionDatas) {
		this.transactionDatas = transactionDatas;
	}
	
	
}
