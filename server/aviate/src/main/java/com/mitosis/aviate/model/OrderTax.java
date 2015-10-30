package com.mitosis.aviate.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Table(name = "order_tax")
@Entity
public class OrderTax {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_tax_id")
	private Long orderTaxId;
	@Column(name="sales_order_id")
	private Long salesOrderId;
	@Column(name="tax_id")
	private Long taxId;
	@Column(name="tax_base_amount")
	private Double taxBaseAmount;
	@Column(name="tax_amount")
	private Double taxAmount;
	@Column(name="created")
	@Temporal(TemporalType.DATE)
	private Date created;
	@Column(name="created_by")
	private Long createdBy;
	@Column(name="updated")
	@Temporal(TemporalType.DATE)
	private Date updated;
	@Column(name="updated_by")
	private Long updatedBy;
	@Column(name="active")
	private boolean active;
	@Column(name="store_id")
	private Long storeId;
	@Column(name="merchant_id")
	private Long merchantId;
	public Long getOrderTaxId() {
		return orderTaxId;
	}
	public void setOrderTaxId(Long orderTaxId) {
		this.orderTaxId = orderTaxId;
	}
	public Long getOrderId() {
		return salesOrderId;
	}
	public void setOrderId(Long orderId) {
		this.salesOrderId = orderId;
	}
	public Long getTaxId() {
		return taxId;
	}
	public void setTaxId(Long taxId) {
		this.taxId = taxId;
	}
	public Double getTaxBaseAmount() {
		return taxBaseAmount;
	}
	public void setTaxBaseAmount(Double taxBaseAmount) {
		this.taxBaseAmount = taxBaseAmount;
	}
	public Double getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	
	
	
}
