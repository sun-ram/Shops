package com.mitosis.shopsbacker.vo.customer;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class OrderTaxVo {

	private Long orderTaxId;
	private Long salesOrderId;
	private Long taxId;
	private Double taxBaseAmount;
	private Double taxAmount;
	private Date created;
	private Long createdBy;
	private Date updated;
	private Long updatedBy;
	private boolean active;
	private Long storeId;
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
