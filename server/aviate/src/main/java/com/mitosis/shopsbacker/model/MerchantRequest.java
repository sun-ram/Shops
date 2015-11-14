package com.mitosis.shopsbacker.model;

// Generated Nov 12, 2015 6:16:19 PM 

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
 * MerchantRequest Created by Sundaram C.
 */
@Entity
@Table(name = "merchant_request", catalog = "shopsbacker")
public class MerchantRequest implements java.io.Serializable {

	private String merchantRequestId;
	private Address address;
	private String name;
	private String email;
	private char isactive;
	private Date created;
	private String createdby;
	private Date updated;
	private String updatedby;

	public MerchantRequest() {
	}

	public MerchantRequest(String merchantRequestId, Address address,
			String name, char isactive) {
		this.merchantRequestId = merchantRequestId;
		this.address = address;
		this.name = name;
		this.isactive = isactive;
	}

	public MerchantRequest(String merchantRequestId, Address address,
			String name, String email, char isactive, Date created,
			String createdby, Date updated, String updatedby) {
		this.merchantRequestId = merchantRequestId;
		this.address = address;
		this.name = name;
		this.email = email;
		this.isactive = isactive;
		this.created = created;
		this.createdby = createdby;
		this.updated = updated;
		this.updatedby = updatedby;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "MERCHANT_REQUEST_ID", unique = true, nullable = false, length = 32)
	public String getMerchantRequestId() {
		return this.merchantRequestId;
	}

	public void setMerchantRequestId(String merchantRequestId) {
		this.merchantRequestId = merchantRequestId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ADDRESS_ID", nullable = false)
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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
