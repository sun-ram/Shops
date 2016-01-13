package com.mitosis.shopsbacker.model;

// Generated Nov 12, 2015 6:16:19 PM 

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * ProductCategory Created by Sundaram C.
 */
@Entity
@Table(name = "product_category")
public class ProductCategory implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String productCategoryId;
	private String updatedby;
	private String createdby;
	private Merchant merchant;
	private ProductCategory parentCategory;
	private Store store;
	private String name;
	private Integer orderSequence;
	private char isactive;
	private Date created;
	private Date updated;
	private List<Product> products = new ArrayList<Product>();
	private List<ProductCategory> productCategories = new ArrayList<ProductCategory>();
	private List<ProductType> productTypes = new ArrayList<ProductType>();

	public ProductCategory() {
	}

	public ProductCategory(String productCategoryId, String updatedby,
			String createdby, String name, char isactive, Date created,
			Date updated) {
		this.productCategoryId = productCategoryId;
		this.updatedby = updatedby;
		this.createdby = createdby;
		this.name = name;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	public ProductCategory(String productCategoryId, String updatedby,
			String createdby, Merchant merchant,
			ProductCategory parentCategory, Store store, String name,
			Integer orderSequence, char isactive, Date created, Date updated,
			List<Product> products, List<ProductCategory> productCategories, List<ProductType> productTypes) {
		this.productCategoryId = productCategoryId;
		this.updatedby = updatedby;
		this.createdby = createdby;
		this.merchant = merchant;
		this.parentCategory = parentCategory;
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
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "PRODUCT_CATEGORY_ID", unique = true, nullable = false, length = 32)
	public String getProductCategoryId() {
		return this.productCategoryId;
	}

	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	@Column(name = "UPDATEDBY", length = 32)
	public String getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
	
	@Column(name = "CREATEDBY", length = 32)
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MERCHANT_ID")
	public Merchant getMerchant() {
		return this.merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PARENT_CATEGORY_ID")
	public ProductCategory getParentCategory() {
		return this.parentCategory;
	}

	public void setParentCategory(ProductCategory parentCategory) {
		this.parentCategory = parentCategory;
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
	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.REMOVE,mappedBy = "parentCategory")
	public List<ProductCategory> getProductCategories() {
		return this.productCategories;
	}

	public void setProductCategories(List<ProductCategory> productCategories) {
		this.productCategories = productCategories;
	}

	@OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.REMOVE,mappedBy = "productCategory")
	public List<ProductType> getProductTypes() {
		return this.productTypes;
	}

	public void setProductTypes(List<ProductType> productTypes) {
		this.productTypes = productTypes;
	}

}
