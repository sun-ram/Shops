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
import javax.persistence.UniqueConstraint;

/**
 * Store Created by Sundaram C.
 */
@Entity
@Table(name = "store", catalog = "shopsbacker", uniqueConstraints = @UniqueConstraint(columnNames = {
		"NAME", "MERCHANT_ID" }))
public class Store implements java.io.Serializable {

	private String storeId;
	private User userByUpdatedby;
	private User userByCreatedby;
	private User userByUserId;
	private Merchant merchant;
	private String name;
	private char isactive;
	private Date created;
	private Date updated;
	private Set warehouses = new HashSet(0);
	private Set orderNumbers = new HashSet(0);
	private Set productCategories = new HashSet(0);
	private Set favourites = new HashSet(0);
	private Set missingProducts = new HashSet(0);
	private Set storagebins = new HashSet(0);
	private Set banners = new HashSet(0);
	private Set salesOrders = new HashSet(0);
	private Set customerFeedbacks = new HashSet(0);
	private Set myCarts = new HashSet(0);
	private Set users = new HashSet(0);
	private Set movements = new HashSet(0);
	private Set productTypes = new HashSet(0);
	private Set productInventories = new HashSet(0);

	public Store() {
	}

	public Store(String storeId, User userByUpdatedby, User userByCreatedby,
			Merchant merchant, String name, char isactive, Date created,
			Date updated) {
		this.storeId = storeId;
		this.userByUpdatedby = userByUpdatedby;
		this.userByCreatedby = userByCreatedby;
		this.merchant = merchant;
		this.name = name;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	public Store(String storeId, User userByUpdatedby, User userByCreatedby,
			User userByUserId, Merchant merchant, String name, char isactive,
			Date created, Date updated, Set warehouses, Set orderNumbers,
			Set productCategories, Set favourites, Set missingProducts,
			Set storagebins, Set banners, Set salesOrders,
			Set customerFeedbacks, Set myCarts, Set users, Set movements,
			Set productTypes, Set productInventories) {
		this.storeId = storeId;
		this.userByUpdatedby = userByUpdatedby;
		this.userByCreatedby = userByCreatedby;
		this.userByUserId = userByUserId;
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
	@Column(name = "STORE_ID", unique = true, nullable = false, length = 32)
	public String getStoreId() {
		return this.storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
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
	@JoinColumn(name = "USER_ID")
	public User getUserByUserId() {
		return this.userByUserId;
	}

	public void setUserByUserId(User userByUserId) {
		this.userByUserId = userByUserId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
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
	public Set getWarehouses() {
		return this.warehouses;
	}

	public void setWarehouses(Set warehouses) {
		this.warehouses = warehouses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
	public Set getOrderNumbers() {
		return this.orderNumbers;
	}

	public void setOrderNumbers(Set orderNumbers) {
		this.orderNumbers = orderNumbers;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
	public Set getProductCategories() {
		return this.productCategories;
	}

	public void setProductCategories(Set productCategories) {
		this.productCategories = productCategories;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
	public Set getFavourites() {
		return this.favourites;
	}

	public void setFavourites(Set favourites) {
		this.favourites = favourites;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
	public Set getMissingProducts() {
		return this.missingProducts;
	}

	public void setMissingProducts(Set missingProducts) {
		this.missingProducts = missingProducts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
	public Set getStoragebins() {
		return this.storagebins;
	}

	public void setStoragebins(Set storagebins) {
		this.storagebins = storagebins;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
	public Set getBanners() {
		return this.banners;
	}

	public void setBanners(Set banners) {
		this.banners = banners;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
	public Set getSalesOrders() {
		return this.salesOrders;
	}

	public void setSalesOrders(Set salesOrders) {
		this.salesOrders = salesOrders;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
	public Set getCustomerFeedbacks() {
		return this.customerFeedbacks;
	}

	public void setCustomerFeedbacks(Set customerFeedbacks) {
		this.customerFeedbacks = customerFeedbacks;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
	public Set getMyCarts() {
		return this.myCarts;
	}

	public void setMyCarts(Set myCarts) {
		this.myCarts = myCarts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
	public Set getUsers() {
		return this.users;
	}

	public void setUsers(Set users) {
		this.users = users;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
	public Set getMovements() {
		return this.movements;
	}

	public void setMovements(Set movements) {
		this.movements = movements;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
	public Set getProductTypes() {
		return this.productTypes;
	}

	public void setProductTypes(Set productTypes) {
		this.productTypes = productTypes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
	public Set getProductInventories() {
		return this.productInventories;
	}

	public void setProductInventories(Set productInventories) {
		this.productInventories = productInventories;
	}

}
