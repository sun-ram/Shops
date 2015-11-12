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
 * MerchantRequest Created by Sundaram C.
 */
@Entity
@Table(name = "merchant_request", catalog = "shopsbacker")
public class MerchantRequest implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String merchantRequestId;
	private String name;
	private String addressId;
	private String email;
	private char isactive;
	private Date created;
	private String createdby;
	private Date updated;
	private String updatedby;

	public MerchantRequest() {
	}

	public MerchantRequest(String merchantRequestId, String name,
			String addressId, char isactive) {
		this.merchantRequestId = merchantRequestId;
		this.name = name;
		this.addressId = addressId;
		this.isactive = isactive;
	}

	public MerchantRequest(String merchantRequestId, String name,
			String addressId, String email, char isactive, Date created,
			String createdby, Date updated, String updatedby) {
		this.merchantRequestId = merchantRequestId;
		this.name = name;
		this.addressId = addressId;
		this.email = email;
		this.isactive = isactive;
		this.created = created;
		this.createdby = createdby;
		this.updated = updated;
		this.updatedby = updatedby;
	}

	@Id
	@Column(name = "MERCHANT_REQUEST_ID", unique = true, nullable = false, length = 32)
	public String getMerchantRequestId() {
		return this.merchantRequestId;
	}

	public void setMerchantRequestId(String merchantRequestId) {
		this.merchantRequestId = merchantRequestId;
	}

	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ADDRESS_ID", nullable = false, length = 32)
	public String getAddressId() {
		return this.addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	@Column(name = "EMAIL", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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
