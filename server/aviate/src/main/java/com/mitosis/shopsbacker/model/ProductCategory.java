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
 * ProductCategory Created by Sundaram C.
 */
@Entity
@Table(name = "product_category", catalog = "shopsbacker")
public class ProductCategory implements java.io.Serializable {

	private String productCategoryId;
	private User userByUpdatedby;
	private User userByCreatedby;
	private Merchant merchant;
	private ProductCategory productCategory;
	private Store store;
	private String name;
	private Integer orderSequence;
	private char isactive;
	private Date created;
	private Date updated;
	private Set products = new HashSet(0);
	private Set productCategories = new HashSet(0);
	private Set productTypes = new HashSet(0);

	public ProductCategory() {
	}

	public ProductCategory(String productCategoryId, User userByUpdatedby,
			User userByCreatedby, String name, char isactive, Date created,
			Date updated) {
		this.productCategoryId = productCategoryId;
		this.userByUpdatedby = userByUpdatedby;
		this.userByCreatedby = userByCreatedby;
		this.name = name;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	public ProductCategory(String productCategoryId, User userByUpdatedby,
			User userByCreatedby, Merchant merchant,
			ProductCategory productCategory, Store store, String name,
			Integer orderSequence, char isactive, Date created, Date updated,
			Set products, Set productCategories, Set productTypes) {
		this.productCategoryId = productCategoryId;
		this.userByUpdatedby = userByUpdatedby;
		this.userByCreatedby = userByCreatedby;
		this.merchant = merchant;
		this.productCategory = productCategory;
		this.store = store;
		this.name = name;
		this.orderSequence = orderSequence;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
		this.products = products;
		this.productCategories = productCategories;
		this.productTypes = productTypes;
	}

	@Id
	@Column(name = "PRODUCT_CATEGORY_ID", unique = true, nullable = false, length = 32)
	public String getProductCategoryId() {
		return this.productCategoryId;
	}

	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
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
	@JoinColumn(name = "MERCHANT_ID")
	public Merchant getMerchant() {
		return this.merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_CATEGORY_ID")
	public ProductCategory getProductCategory() {
		return this.productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
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

	@Column(name = "ORDER_SEQUENCE")
	public Integer getOrderSequence() {
		return this.orderSequence;
	}

	public void setOrderSequence(Integer orderSequence) {
		this.orderSequence = orderSequence;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productCategory")
	public Set getProducts() {
		return this.products;
	}

	public void setProducts(Set products) {
		this.products = products;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productCategory")
	public Set getProductCategories() {
		return this.productCategories;
	}

	public void setProductCategories(Set productCategories) {
		this.productCategories = productCategories;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productCategory")
	public Set getProductTypes() {
		return this.productTypes;
	}

	public void setProductTypes(Set productTypes) {
		this.productTypes = productTypes;
	}

}
