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
 * Merchant Created by Sundaram C.
 */
@Entity
@Table(name = "merchant", catalog = "shopsbacker", uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
public class Merchant implements java.io.Serializable {

	private String merchantId;
	private User userByUpdatedby;
	private User userByCreatedby;
	private String name;
	private String userId;
	private String logo;
	private char isactive;
	private Date created;
	private Date updated;
	private Set productCategories = new HashSet(0);
	private Set favourites = new HashSet(0);
	private Set productTypes = new HashSet(0);
	private Set banners = new HashSet(0);
	private Set discounts = new HashSet(0);
	private Set stores = new HashSet(0);
	private Set taxes = new HashSet(0);
	private Set warehouses = new HashSet(0);
	private Set shippingChargeses = new HashSet(0);
	private Set myCarts = new HashSet(0);
	private Set orderTaxes = new HashSet(0);
	private Set storagebins = new HashSet(0);
	private Set orderNumbers = new HashSet(0);
	private Set users = new HashSet(0);
	private Set productInventories = new HashSet(0);
	private Set movements = new HashSet(0);
	private Set products = new HashSet(0);
	private Set missingProducts = new HashSet(0);
	private Set customerFeedbacks = new HashSet(0);
	private Set deliveryTimeSlots = new HashSet(0);
	private Set productOffers = new HashSet(0);

	public Merchant() {
	}

	public Merchant(String merchantId, User userByUpdatedby,
			User userByCreatedby, String name, char isactive, Date created,
			Date updated) {
		this.merchantId = merchantId;
		this.userByUpdatedby = userByUpdatedby;
		this.userByCreatedby = userByCreatedby;
		this.name = name;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	public Merchant(String merchantId, User userByUpdatedby,
			User userByCreatedby, String name, String userId, String logo,
			char isactive, Date created, Date updated, Set productCategories,
			Set favourites, Set productTypes, Set banners, Set discounts,
			Set stores, Set taxes, Set warehouses, Set shippingChargeses,
			Set myCarts, Set orderTaxes, Set storagebins, Set orderNumbers,
			Set users, Set productInventories, Set movements, Set products,
			Set missingProducts, Set customerFeedbacks, Set deliveryTimeSlots,
			Set productOffers) {
		this.merchantId = merchantId;
		this.userByUpdatedby = userByUpdatedby;
		this.userByCreatedby = userByCreatedby;
		this.name = name;
		this.userId = userId;
		this.logo = logo;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
		this.productCategories = productCategories;
		this.favourites = favourites;
		this.productTypes = productTypes;
		this.banners = banners;
		this.discounts = discounts;
		this.stores = stores;
		this.taxes = taxes;
		this.warehouses = warehouses;
		this.shippingChargeses = shippingChargeses;
		this.myCarts = myCarts;
		this.orderTaxes = orderTaxes;
		this.storagebins = storagebins;
		this.orderNumbers = orderNumbers;
		this.users = users;
		this.productInventories = productInventories;
		this.movements = movements;
		this.products = products;
		this.missingProducts = missingProducts;
		this.customerFeedbacks = customerFeedbacks;
		this.deliveryTimeSlots = deliveryTimeSlots;
		this.productOffers = productOffers;
	}

	@Id
	@Column(name = "MERCHANT_ID", unique = true, nullable = false, length = 32)
	public String getMerchantId() {
		return this.merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
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

	@Column(name = "NAME", unique = true, nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "USER_ID", length = 32)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "LOGO", length = 32)
	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public Set getProductCategories() {
		return this.productCategories;
	}

	public void setProductCategories(Set productCategories) {
		this.productCategories = productCategories;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public Set getFavourites() {
		return this.favourites;
	}

	public void setFavourites(Set favourites) {
		this.favourites = favourites;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public Set getProductTypes() {
		return this.productTypes;
	}

	public void setProductTypes(Set productTypes) {
		this.productTypes = productTypes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public Set getBanners() {
		return this.banners;
	}

	public void setBanners(Set banners) {
		this.banners = banners;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public Set getDiscounts() {
		return this.discounts;
	}

	public void setDiscounts(Set discounts) {
		this.discounts = discounts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public Set getStores() {
		return this.stores;
	}

	public void setStores(Set stores) {
		this.stores = stores;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public Set getTaxes() {
		return this.taxes;
	}

	public void setTaxes(Set taxes) {
		this.taxes = taxes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public Set getWarehouses() {
		return this.warehouses;
	}

	public void setWarehouses(Set warehouses) {
		this.warehouses = warehouses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public Set getShippingChargeses() {
		return this.shippingChargeses;
	}

	public void setShippingChargeses(Set shippingChargeses) {
		this.shippingChargeses = shippingChargeses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public Set getMyCarts() {
		return this.myCarts;
	}

	public void setMyCarts(Set myCarts) {
		this.myCarts = myCarts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public Set getOrderTaxes() {
		return this.orderTaxes;
	}

	public void setOrderTaxes(Set orderTaxes) {
		this.orderTaxes = orderTaxes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public Set getStoragebins() {
		return this.storagebins;
	}

	public void setStoragebins(Set storagebins) {
		this.storagebins = storagebins;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public Set getOrderNumbers() {
		return this.orderNumbers;
	}

	public void setOrderNumbers(Set orderNumbers) {
		this.orderNumbers = orderNumbers;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public Set getUsers() {
		return this.users;
	}

	public void setUsers(Set users) {
		this.users = users;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public Set getProductInventories() {
		return this.productInventories;
	}

	public void setProductInventories(Set productInventories) {
		this.productInventories = productInventories;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public Set getMovements() {
		return this.movements;
	}

	public void setMovements(Set movements) {
		this.movements = movements;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public Set getProducts() {
		return this.products;
	}

	public void setProducts(Set products) {
		this.products = products;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public Set getMissingProducts() {
		return this.missingProducts;
	}

	public void setMissingProducts(Set missingProducts) {
		this.missingProducts = missingProducts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public Set getCustomerFeedbacks() {
		return this.customerFeedbacks;
	}

	public void setCustomerFeedbacks(Set customerFeedbacks) {
		this.customerFeedbacks = customerFeedbacks;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public Set getDeliveryTimeSlots() {
		return this.deliveryTimeSlots;
	}

	public void setDeliveryTimeSlots(Set deliveryTimeSlots) {
		this.deliveryTimeSlots = deliveryTimeSlots;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public Set getProductOffers() {
		return this.productOffers;
	}

	public void setProductOffers(Set productOffers) {
		this.productOffers = productOffers;
	}

}
