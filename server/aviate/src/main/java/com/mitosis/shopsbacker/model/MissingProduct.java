package com.mitosis.shopsbacker.model;

// Generated Nov 6, 2015 7:27:52 PM 

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * MissingProduct Created by Sundaram C.
 */
@Entity
@Table(name = "missing_product", catalog = "shopsbacker")
public class MissingProduct implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String missingProductId;
	private String name;
	private String description;
	private String customerId;
	private String merchantId;
	private String storeId;
	private char isactive;
	private Date created;
	private String createdby;
	private Date updated;
	private String updatedby;

	public MissingProduct() {
	}

	public MissingProduct(String missingProductId, String name,
			String customerId, String merchantId, char isactive) {
		this.missingProductId = missingProductId;
		this.name = name;
		this.customerId = customerId;
		this.merchantId = merchantId;
		this.isactive = isactive;
	}

	public MissingProduct(String missingProductId, String name,
			String description, String customerId, String merchantId,
			String storeId, char isactive, Date created, String createdby,
			Date updated, String updatedby) {
		this.missingProductId = missingProductId;
		this.name = name;
		this.description = description;
		this.customerId = customerId;
		this.merchantId = merchantId;
		this.storeId = storeId;
		this.isactive = isactive;
		this.created = created;
		this.createdby = createdby;
		this.updated = updated;
		this.updatedby = updatedby;
	}

	@Id
	@Column(name = "MISSING_PRODUCT_ID", unique = true, nullable = false, length = 32)
	public String getMissingProductId() {
		return this.missingProductId;
	}

	public void setMissingProductId(String missingProductId) {
		this.missingProductId = missingProductId;
	}

	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION", length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "CUSTOMER_ID", nullable = false, length = 32)
	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Column(name = "MERCHANT_ID", nullable = false, length = 32)
	public String getMerchantId() {
		return this.merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	@Column(name = "STORE_ID", length = 32)
	public String getStoreId() {
		return this.storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
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
