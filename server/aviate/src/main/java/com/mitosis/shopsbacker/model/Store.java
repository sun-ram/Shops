package com.mitosis.shopsbacker.model;

// Generated Nov 12, 2015 6:16:19 PM 

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

/**
 * Store Created by Sundaram C.
 */
@Entity
@Table(name = "store", catalog = "shopsbacker", uniqueConstraints = @UniqueConstraint(columnNames = {
		"NAME", "MERCHANT_ID" }))
public class Store implements java.io.Serializable {

	private String storeId;
	private String updatedby;
	private String createdby;
	private User user;
	private Merchant merchant;
	private String name;
	private char isactive;
	private Date created;
	private Date updated;
	private List<Warehouse> warehouses = new ArrayList<Warehouse>();
	private List<OrderNumber> orderNumbers = new ArrayList<OrderNumber>();
	private List<ProductCategory> productCategories = new ArrayList<ProductCategory>();
	private List<Favourite> favourites = new ArrayList<Favourite>();
	private List<MissingProduct> missingProducts = new ArrayList<MissingProduct>();
	private List<Storagebin> storagebins = new ArrayList<Storagebin>();
	private List<Banner> banners = new ArrayList<Banner>();
	private List<SalesOrder> salesOrders = new ArrayList<SalesOrder>();
	private List<CustomerFeedback> customerFeedbacks = new ArrayList<CustomerFeedback>();
	private List<MyCart> myCarts = new ArrayList<MyCart>();
	private List<User> users = new ArrayList<User>();
	private List<Movement> movements = new ArrayList<Movement>();
	private List<ProductType> productTypes = new ArrayList<ProductType>();
	private List<ProductInventory> productInventories = new ArrayList<ProductInventory>();

	public Store() {
	}

	public Store(String storeId, String updatedby, String createdby,
			Merchant merchant, String name, char isactive, Date created,
			Date updated) {
		this.storeId = storeId;
		this.updatedby = updatedby;
		this.createdby = createdby;
		this.merchant = merchant;
		this.name = name;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	public Store(String storeId, String updatedby, String createdby,
			User user, Merchant merchant, String name, char isactive,
			Date created, Date updated, List<Warehouse> warehouses, List<OrderNumber> orderNumbers,
			List<ProductCategory> productCategories, List<Favourite> favourites, List<MissingProduct> missingProducts,
			List<Storagebin> storagebins, List<Banner> banners, List<SalesOrder> salesOrders,
			List<CustomerFeedback> customerFeedbacks, List<MyCart> myCarts, List<User> users, List<Movement> movements,
			List<ProductType> productTypes, List<ProductInventory> productInventories) {
		this.storeId = storeId;
		this.updatedby = updatedby;
		this.createdby = createdby;
		this.user = user;
		this.merchant = merchant;
		this.name = name;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
		this.warehouses = warehouses;
		this.orderNumbers = orderNumbers;
		this.productCategories = productCategories;
		this.favourites = favourites;
		this.missingProducts = missingProducts;
		this.storagebins = storagebins;
		this.banners = banners;
		this.salesOrders = salesOrders;
		this.customerFeedbacks = customerFeedbacks;
		this.myCarts = myCarts;
		this.users = users;
		this.movements = movements;
		this.productTypes = productTypes;
		this.productInventories = productInventories;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "STORE_ID", unique = true, nullable = false, length = 32)
	public String getStoreId() {
		return this.storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	@Column(name = "UPDATEDBY", length = 32)
	public String getUpdatedby() {
		return this.updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
	
	@Column(name = "CREATEDBY", length = 32)
	public String getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn(name = "MERCHANT_ID", nullable = false)
	public Merchant getMerchant() {
		return this.merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
	public List<Warehouse> getWarehouses() {
		return this.warehouses;
	}

	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
	public List<OrderNumber> getOrderNumbers() {
		return this.orderNumbers;
	}

	public void setOrderNumbers(List<OrderNumber> orderNumbers) {
		this.orderNumbers = orderNumbers;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
	public List<ProductCategory> getProductCategories() {
		return this.productCategories;
	}

	public void setProductCategories(List<ProductCategory> productCategories) {
		this.productCategories = productCategories;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
	public List<Favourite> getFavourites() {
		return this.favourites;
	}

	public void setFavourites(List<Favourite> favourites) {
		this.favourites = favourites;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
	public List<MissingProduct> getMissingProducts() {
		return this.missingProducts;
	}

	public void setMissingProducts(List<MissingProduct> missingProducts) {
		this.missingProducts = missingProducts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
	public List<Storagebin> getStoragebins() {
		return this.storagebins;
	}

	public void setStoragebins(List<Storagebin> storagebins) {
		this.storagebins = storagebins;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
	public List<Banner> getBanners() {
		return this.banners;
	}

	public void setBanners(List<Banner> banners) {
		this.banners = banners;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
	public List<SalesOrder> getSalesOrders() {
		return this.salesOrders;
	}

	public void setSalesOrders(List<SalesOrder> salesOrders) {
		this.salesOrders = salesOrders;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
	public List<CustomerFeedback> getCustomerFeedbacks() {
		return this.customerFeedbacks;
	}

	public void setCustomerFeedbacks(List<CustomerFeedback> customerFeedbacks) {
		this.customerFeedbacks = customerFeedbacks;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
	public List<MyCart> getMyCarts() {
		return this.myCarts;
	}

	public void setMyCarts(List<MyCart> myCarts) {
		this.myCarts = myCarts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
	public List<Movement> getMovements() {
		return this.movements;
	}

	public void setMovements(List<Movement> movements) {
		this.movements = movements;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
	public List<ProductType> getProductTypes() {
		return this.productTypes;
	}

	public void setProductTypes(List<ProductType> productTypes) {
		this.productTypes = productTypes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
	public List<ProductInventory> getProductInventories() {
		return this.productInventories;
	}

	public void setProductInventories(List<ProductInventory> productInventories) {
		this.productInventories = productInventories;
	}

}
