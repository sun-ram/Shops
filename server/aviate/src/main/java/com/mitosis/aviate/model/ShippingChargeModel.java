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
@Table(name = "shipping_charge")
@Entity
public class ShippingChargeModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="shipping_charge_id")
	private Long shippingChargeId;
	@Column(name="shipping_company")
	private String shippingCompany;
	@Column(name="shipping_charge")
	private String shippingCharge;
	@Column(name="shipping_service")
	private String shippingService;
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
	public Long getShippingChargeId() {
		return shippingChargeId;
	}
	public void setShippingChargeId(Long shippingChargeId) {
		this.shippingChargeId = shippingChargeId;
	}
	public String getShippingCompany() {
		return shippingCompany;
	}
	public void setShippingCompany(String shippingCompany) {
		this.shippingCompany = shippingCompany;
	}
	public String getShippingCharge() {
		return shippingCharge;
	}
	public void setShippingCharge(String shippingCharge) {
		this.shippingCharge = shippingCharge;
	}
	public String getShippingService() {
		return shippingService;
	}
	public void setShippingService(String shippingService) {
		this.shippingService = shippingService;
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
