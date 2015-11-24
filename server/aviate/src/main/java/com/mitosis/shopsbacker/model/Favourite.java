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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

/**
 * Favourite Created by Sundaram C.
 */
@Entity
@Table(name = "favourite", catalog = "shopsbacker", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "NAME", "CUSTOMER_ID", "MERCHANT_ID",
				"STORE_ID" }),
		@UniqueConstraint(columnNames = { "NAME", "CUSTOMER_ID" }) })
public class Favourite implements java.io.Serializable {

	private String favouriteId;
	private Merchant merchant;
	private String createdby;
	private SalesOrder salesOrder;
	private Customer customer;
	private String updatedby;
	private Store store;
	private String name;
	private char isactive;
	private Date created;
	private Date updated;

	public Favourite() {
	}

	public Favourite(String favouriteId, Merchant merchant,
			String createdby, SalesOrder salesOrder,
			Customer customer, String updatedby,
			Store store, String name, char isactive, Date created, Date updated) {
		this.favouriteId = favouriteId;
		this.merchant = merchant;
		this.createdby = createdby;
		this.salesOrder = salesOrder;
		this.customer = customer;
		this.updatedby = updatedby;
		this.store = store;
		this.name = name;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "FAVOURITE_ID", unique = true, nullable = false, length = 32)
	public String getFavouriteId() {
		return this.favouriteId;
	}

	public void setFavouriteId(String favouriteId) {
		this.favouriteId = favouriteId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MERCHANT_ID", nullable = false)
	public Merchant getMerchant() {
		return this.merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	
	@Column(name = "CREATEDBY", length = 32)
	public String getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	@ManyToOne
	@JoinColumn(name = "ORDER_ID", nullable = false)
	public SalesOrder getSalesOrder() {
		return this.salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID", nullable = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@Column(name = "UPDATEDBY", length = 32)
	public String getUpdatedby() {
		return this.updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STORE_ID", nullable = false)
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

	@Column(name = "ISACTIVE", nullable = false, length = 1)
	public char getIsactive() {
		return this.isactive;
	}

	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED", nullable = false, length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED", nullable = false, length = 19)
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}
