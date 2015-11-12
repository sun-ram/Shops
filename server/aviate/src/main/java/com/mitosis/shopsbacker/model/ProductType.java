package com.mitosis.shopsbacker.model;

// Generated Nov 12, 2015 3:32:23 PM 

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ProductType Created by Sundaram C.
 */
@Entity
@Table(name = "product_type", catalog = "shopsbacker")
public class ProductType implements java.io.Serializable {

	private String productTypeId;
	private User userByUpdatedby;
	private User userByCreatedby;
	private Merchant merchant;
	private ProductCategory productCategory;
	private Store store;
	private String name;
	private char isactive;
	private Date created;
	private Date updated;
	private Set products = new HashSet(0);

	public ProductType() {
	}

	public ProductType(String productTypeId, User userByUpdatedby,
			User userByCreatedby, Merchant merchant,
			ProductCategory productCategory, Store store, char isactive,
			Date created, Date updated) {
		this.productTypeId = productTypeId;
		this.userByUpdatedby = userByUpdatedby;
		this.userByCreatedby = userByCreatedby;
		this.merchant = merchant;
		this.productCategory = productCategory;
		this.store = store;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	public ProductType(String productTypeId, User userByUpdatedby,
			User userByCreatedby, Merchant merchant,
			ProductCategory productCategory, Store store, String name,
			char isactive, Date created, Date updated, Set products) {
		this.productTypeId = productTypeId;
		this.userByUpdatedby = userByUpdatedby;
		this.userByCreatedby = userByCreatedby;
		this.merchant = merchant;
		this.productCategory = productCategory;
		this.store = store;
		this.name = name;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
		this.products = products;
	}

	@Id
	@Column(name = "PRODUCT_TYPE_ID", unique = true, nullable = false, length = 32)
	public String getProductTypeId() {
		return this.productTypeId;
	}

	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
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
	@JoinColumn(name = "CREATEDBY", nullable = false)
	public User getUserByCreatedby() {
		return this.userByCreatedby;
	}

	public void setUserByCreatedby(User userByCreatedby) {
		this.userByCreatedby = userByCreatedby;
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
	@JoinColumn(name = "PRODUCT_CATEGORY_ID", nullable = false)
	public ProductCategory getProductCategory() {
		return this.productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STORE_ID", nullable = false)
	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	@Column(name = "NAME", length = 100)
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productType")
	public Set getProducts() {
		return this.products;
	}

	public void setProducts(Set products) {
		this.products = products;
	}

}
