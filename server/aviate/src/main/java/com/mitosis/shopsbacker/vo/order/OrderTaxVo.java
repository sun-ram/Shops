package com.mitosis.shopsbacker.vo.order;

import java.math.BigDecimal;

import com.mitosis.shopsbacker.model.Tax;

public class OrderTaxVo {

	private String orderTaxId;
	private Tax tax;
	private BigDecimal taxAmount;
	public String getOrderTaxId() {
		return orderTaxId;
	}
	public void setOrderTaxId(String orderTaxId) {
		this.orderTaxId = orderTaxId;
	}
	public Tax getTax() {
		return tax;
	}
	public void setTax(Tax tax) {
		this.tax = tax;
	}
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}
}
