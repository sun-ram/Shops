package com.mitosis.shopsbacker.model;

// Generated Nov 6, 2015 7:27:52 PM 

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
 * ProductInventory Created by Sundaram C.
 */
@Entity
@Table(name = "product_inventory", catalog = "shopsbacker", uniqueConstraints = @UniqueConstraint(columnNames = {
		"MERCHANT_ID", "STORE_ID", "STORAGEBIN_ID", "PRODUCT_ID" }))
public class ProductInventory implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String productInventoryId;
	private User userByCreatedby;
	private User userByUpdatedby;
	private Storagebin storagebin;
	private Merchant merchant;
	private Product product;
	private Store store;
	private int availableQty;
	private char isactive;
	private Date created;
	private Date updated;

	public ProductInventory() {
	}

	public ProductInventory(String productInventoryId, User userByCreatedby,
			User userByUpdatedby, Storagebin storagebin, Merchant merchant,
			Product product, Store store, int availableQty, char isactive,
			Date created, Date updated) {
		this.productInventoryId = productInventoryId;
		this.userByCreatedby = userByCreatedby;
		this.userByUpdatedby = userByUpdatedby;
		this.storagebin = storagebin;
		this.merchant = merchant;
		this.product = product;
		this.store = store;
		this.availableQty = availableQty;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	@Id
	@Column(name = "PRODUCT_INVENTORY_ID", unique = true, nullable = false, length = 32)
	public String getProductInventoryId() {
		return this.productInventoryId;
	}

	public void setProductInventoryId(String productInventoryId) {
		this.productInventoryId = productInventoryId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATEDBY", nullable = false)
	public User getUserByCreatedby() {
		return this.userByCreatedby;
	}

	public void setUserByCreatedby(User userByCreatedby) {
		this.userByCreatedby = userByCreatedby;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UPDATEDBY", nullable = false)
	public User getUserByUpdatedby() {
		return this.userByUpdatedby;
	}

	public void setUserByUpdatedby(User userByUpdatedby) {
		this.userByUpdatedby = userByUpdatedby;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STORAGEBIN_ID", nullable = false)
	public Storagebin getStoragebin() {
		return this.storagebin;
	}

	public void setStoragebin(Storagebin storagebin) {
		this.storagebin = storagebin;
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
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STORE_ID", nullable = false)
	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	@Column(name = "AVAILABLE_QTY", nullable = false)
	public int getAvailableQty() {
		return this.availableQty;
	}

	public void setAvailableQty(int availableQty) {
		this.availableQty = availableQty;
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
