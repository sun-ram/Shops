package com.mitosis.shopsbacker.model;

// Generated Nov 12, 2015 6:16:19 PM 

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * OrderTax Created by Sundaram C.
 */
@Entity
@Table(name = "order_tax", catalog = "shopsbacker")
public class OrderTax implements java.io.Serializable {

	private String orderTaxId;
	private Merchant merchant;
	private Tax tax;
	private SalesOrder salesOrder;
	private BigDecimal taxAmount;
	private char isactive;
	private Date created;
	private String createdby;
	private Date updated;
	private String updatedby;

	public OrderTax() {
	}

	public OrderTax(String orderTaxId, Merchant merchant, Tax tax,
			SalesOrder salesOrder, BigDecimal taxAmount, char isactive) {
		this.orderTaxId = orderTaxId;
		this.merchant = merchant;
		this.tax = tax;
		this.salesOrder = salesOrder;
		this.taxAmount = taxAmount;
		this.isactive = isactive;
	}

	public OrderTax(String orderTaxId, Merchant merchant, Tax tax,
			SalesOrder salesOrder, BigDecimal taxAmount, char isactive,
			Date created, String createdby, Date updated, String updatedby) {
		this.orderTaxId = orderTaxId;
		this.merchant = merchant;
		this.tax = tax;
		this.salesOrder = salesOrder;
		this.taxAmount = taxAmount;
		this.isactive = isactive;
		this.created = created;
		this.createdby = createdby;
		this.updated = updated;
		this.updatedby = updatedby;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ORDER_TAX_ID", unique = true, nullable = false, length = 32)
	public String getOrderTaxId() {
		return this.orderTaxId;
	}

	public void setOrderTaxId(String orderTaxId) {
		this.orderTaxId = orderTaxId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MERCHANT_ID", nullable = false)
	public Merchant getMerchant() {
		return this.merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TAX_ID", nullable = false)
	public Tax getTax() {
		return this.tax;
	}

	public void setTax(Tax tax) {
		this.tax = tax;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SALES_ORDER_ID", nullable = false)
	public SalesOrder getSalesOrder() {
		return this.salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}

	@Column(name = "TAX_AMOUNT", nullable = false, precision = 15)
	public BigDecimal getTaxAmount() {
		return this.taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	@Column(name = "ISACTIVE", nullable = false, length = 1)
	public char getIsactive() {
		return this.isactive;
	}

	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED", length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name = "CREATEDBY", length = 32)
	public String getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED", length = 19)
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Column(name = "UPDATEDBY", length = 32)
	public String getUpdatedby() {
		return this.updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

}
