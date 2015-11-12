package com.mitosis.shopsbacker.model;

// Generated Nov 6, 2015 7:27:52 PM 

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OrderTax Created by Sundaram C.
 */
@Entity
@Table(name = "order_tax", catalog = "shopsbacker")
public class OrderTax implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderTaxId;
	private String salesOrderId;
	private String taxId;
	private BigDecimal taxAmount;
	private String merchantId;

	public OrderTax() {
	}

	public OrderTax(String orderTaxId, String salesOrderId, String taxId,
			BigDecimal taxAmount, String merchantId) {
		this.orderTaxId = orderTaxId;
		this.salesOrderId = salesOrderId;
		this.taxId = taxId;
		this.taxAmount = taxAmount;
		this.merchantId = merchantId;
	}

	@Id
	@Column(name = "ORDER_TAX_ID", unique = true, nullable = false, length = 32)
	public String getOrderTaxId() {
		return this.orderTaxId;
	}

	public void setOrderTaxId(String orderTaxId) {
		this.orderTaxId = orderTaxId;
	}

	@Column(name = "SALES_ORDER_ID", nullable = false, length = 32)
	public String getSalesOrderId() {
		return this.salesOrderId;
	}

	public void setSalesOrderId(String salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

	@Column(name = "TAX_ID", nullable = false, length = 32)
	public String getTaxId() {
		return this.taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	@Column(name = "TAX_AMOUNT", nullable = false, precision = 15)
	public BigDecimal getTaxAmount() {
		return this.taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	@Column(name = "MERCHANT_ID", nullable = false, length = 32)
	public String getMerchantId() {
		return this.merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

}
