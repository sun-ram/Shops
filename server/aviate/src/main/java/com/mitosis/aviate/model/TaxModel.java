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
@Table(name = "tax")
@Entity
public class TaxModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="tax_id")
	private Long taxId;
	@Column(name="tax_name")
	private String taxName;
	@Column(name="is_summary")
	private boolean isSummary;
	@Column(name="rate")
	private Double rate;
	@Column(name="parent_tax_id")
	private Long parentTaxId;
	@Column(name="store_id")
	private Long storeId;
	@Column(name="merchant_id")
	private Long merchantId;
	@Column(name="created")
	@Temporal(TemporalType.DATE)
	private Date created;
	@Column(name="created_by")
	private String createdBy;
	@Column(name="updated")
	@Temporal(TemporalType.DATE)
	private Date updated;
	@Column(name="updated_by")
	private String updatedBy;
	@Column(name="active")
	private boolean active;
	public Long getTaxId() {
		return taxId;
	}
	public void setTaxId(Long taxId) {
		this.taxId = taxId;
	}
	public String getTaxName() {
		return taxName;
	}
	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}
	public boolean isSummary() {
		return isSummary;
	}
	public void setSummary(boolean isSummary) {
		this.isSummary = isSummary;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Long getParentTaxId() {
		return parentTaxId;
	}
	public void setParentTaxId(Long parentTaxId) {
		this.parentTaxId = parentTaxId;
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
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
}
