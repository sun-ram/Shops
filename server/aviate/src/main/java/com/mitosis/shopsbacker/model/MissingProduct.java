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
 * MissingProduct Created by Sundaram C.
 */
@Entity
@Table(name = "missing_product", catalog = "shopsbacker")
public class MissingProduct implements java.io.Serializable {

	private String missingProductId;
	private Merchant merchant;
	private Customer customer;
	private Store store;
	private String name;
	private String description;
	private char isactive;
	private Date created;
	private String createdby;
	private Date updated;
	private String updatedby;

	public MissingProduct() {
	}

	public MissingProduct(String missingProductId, Merchant merchant,
			Customer customer, String name, char isactive) {
		this.missingProductId = missingProductId;
		this.merchant = merchant;
		this.customer = customer;
		this.name = name;
		this.isactive = isactive;
	}

	public MissingProduct(String missingProductId, Merchant merchant,
			Customer customer, Store store, String name, String description,
			char isactive, Date created, String createdby, Date updated,
			String updatedby) {
		this.missingProductId = missingProductId;
		this.merchant = merchant;
		this.customer = customer;
		this.store = store;
		this.name = name;
		this.description = description;
		this.isactive = isactive;
		this.created = created;
		this.createdby = createdby;
		this.updated = updated;
		this.updatedby = updatedby;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "MISSING_PRODUCT_ID", unique = true, nullable = false, length = 32)
	public String getMissingProductId() {
		return this.missingProductId;
	}

	public void setMissingProductId(String missingProductId) {
		this.missingProductId = missingProductId;
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
	@JoinColumn(name = "CUSTOMER_ID", nullable = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STORE_ID")
	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
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
