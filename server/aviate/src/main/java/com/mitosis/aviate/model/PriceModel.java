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
@Entity
@Table(name="price")
public class PriceModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="price_id")
	private Long priceId;
	@Column(name="merchant_id")
	private Long  merchantId;
	@Column(name="store_id")
	private Long storeId;
	@Column(name="product_id")
	private Long productId;
	@Column(name="price")
	private Double price;
	@Column(name="active")
	private boolean active;
	@Temporal(TemporalType.DATE)
	@Column(name="created")
	private Date created;
	@Column(name="created_by")
	private Long createdBy;
	@Temporal(TemporalType.DATE)
	@Column (name="updated")
	private Date updated;
	@Column(name="updated_by")
	private Long updatedBy;
	public Long getPriceId() {
		return priceId;
	}
	public Long getMerchantId() {
		return merchantId;
	}
	public Long getStoreId() {
		return storeId;
	}
	public Long getProductId() {
		return productId;
	}
	public Double getPrice() {
		return price;
	}
	public boolean isActive() {
		return active;
	}
	public Date getCreated() {
		return created;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public Date getUpdated() {
		return updated;
	}
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setPriceId(Long priceId) {
		this.priceId = priceId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
}
