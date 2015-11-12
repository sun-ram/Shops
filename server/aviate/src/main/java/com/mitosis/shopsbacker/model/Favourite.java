package com.mitosis.shopsbacker.model;

// Generated Nov 12, 2015 6:16:19 PM 

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

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
	private Customer customerByCreatedby;
	private SalesOrder salesOrder;
	private Customer customerByCustomerId;
	private Customer customerByUpdatedby;
	private Store store;
	private String name;
	private char isactive;
	private Date created;
	private Date updated;

	public Favourite() {
	}

	public Favourite(String favouriteId, Merchant merchant,
			Customer customerByCreatedby, SalesOrder salesOrder,
			Customer customerByCustomerId, Customer customerByUpdatedby,
			Store store, String name, char isactive, Date created, Date updated) {
		this.favouriteId = favouriteId;
		this.merchant = merchant;
		this.customerByCreatedby = customerByCreatedby;
		this.salesOrder = salesOrder;
		this.customerByCustomerId = customerByCustomerId;
		this.customerByUpdatedby = customerByUpdatedby;
		this.store = store;
		this.name = name;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	@Id
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATEDBY", nullable = false)
	public Customer getCustomerByCreatedby() {
		return this.customerByCreatedby;
	}

	public void setCustomerByCreatedby(Customer customerByCreatedby) {
		this.customerByCreatedby = customerByCreatedby;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ID", nullable = false)
	public SalesOrder getSalesOrder() {
		return this.salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID", nullable = false)
	public Customer getCustomerByCustomerId() {
		return this.customerByCustomerId;
	}

	public void setCustomerByCustomerId(Customer customerByCustomerId) {
		this.customerByCustomerId = customerByCustomerId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UPDATEDBY", nullable = false)
	public Customer getCustomerByUpdatedby() {
		return this.customerByUpdatedby;
	}

	public void setCustomerByUpdatedby(Customer customerByUpdatedby) {
		this.customerByUpdatedby = customerByUpdatedby;
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
