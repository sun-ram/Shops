package com.mitosis.shopsbacker.model;

// Generated Nov 6, 2015 7:27:52 PM 

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String merchantId;
	private User userByUpdatedby;
	private User userByCreatedby;
	private String name;
	private String userId;
	private String logo;
	private char isactive;
	private Date created;
	private Date updated;
	
	private List<ProductCategory> productCategories = new ArrayList<ProductCategory>();
	private List<ProductType> productTypes = new ArrayList<ProductType>();
	private List<Store> stores = new ArrayList<Store>();
	private List<Tax> taxes = new ArrayList<Tax>();
	private List<Warehouse> warehouses = new ArrayList<Warehouse>();
	private List<MyCart> myCarts = new ArrayList<MyCart>();
	private List<Storagebin> storagebins = new ArrayList<Storagebin>();
	private List<OrderNumber> orderNumbers = new ArrayList<OrderNumber>();
	private List<User> users = new ArrayList<User>();
	private List<ProductInventory> productInventories = new ArrayList<ProductInventory>();
	private List<Movement> movements = new ArrayList<Movement>();
	private List<Product> products = new ArrayList<Product>();
	private List<CustomerFeedback> customerFeedbacks = new ArrayList<CustomerFeedback>();
	private List<DeliveryTimeSlot> deliveryTimeSlots = new ArrayList<DeliveryTimeSlot>();
	private List<ProductOffer> productOffers = new ArrayList<ProductOffer>();
	
	
	
	

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
			char isactive, Date created, Date updated, List<ProductCategory> productCategories,
			List<ProductType> productTypes, List<Store> stores, List<Tax> taxes, List<Warehouse> warehouses,
			List<MyCart> myCarts, List<Storagebin> storagebins, List<OrderNumber> orderNumbers, List<User> users,
			List<ProductInventory> productInventories, List<Movement> movements, List<Product> products,
			List<CustomerFeedback> customerFeedbacks, List<DeliveryTimeSlot> deliveryTimeSlots, List<ProductOffer> productOffers) {
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
		this.productTypes = productTypes;
		this.stores = stores;
		this.taxes = taxes;
		this.warehouses = warehouses;
		this.myCarts = myCarts;
		this.storagebins = storagebins;
		this.orderNumbers = orderNumbers;
		this.users = users;
		this.productInventories = productInventories;
		this.movements = movements;
		this.products = products;
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
	public List<ProductCategory> getProductCategories() {
		return this.productCategories;
	}

	public void setProductCategories(List<ProductCategory> productCategories) {
		this.productCategories = productCategories;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public List<ProductType> getProductTypes() {
		return this.productTypes;
	}

	public void setProductTypes(List<ProductType> productTypes) {
		this.productTypes = productTypes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public List<Store> getStores() {
		return this.stores;
	}

	public void setStores(List<Store> stores) {
		this.stores = stores;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public List<Tax> getTaxes() {
		return this.taxes;
	}

	public void setTaxes(List<Tax> taxes) {
		this.taxes = taxes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public List<Warehouse> getWarehouses() {
		return this.warehouses;
	}

	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public List<MyCart> getMyCarts() {
		return this.myCarts;
	}

	public void setMyCarts(List<MyCart> myCarts) {
		this.myCarts = myCarts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public List<Storagebin> getStoragebins() {
		return this.storagebins;
	}

	public void setStoragebins(List<Storagebin> storagebins) {
		this.storagebins = storagebins;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public List<OrderNumber> getOrderNumbers() {
		return this.orderNumbers;
	}

	public void setOrderNumbers(List<OrderNumber> orderNumbers) {
		this.orderNumbers = orderNumbers;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public List<ProductInventory> getProductInventories() {
		return this.productInventories;
	}

	public void setProductInventories(List<ProductInventory> productInventories) {
		this.productInventories = productInventories;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public List<Movement> getMovements() {
		return this.movements;
	}

	public void setMovements(List<Movement> movements) {
		this.movements = movements;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public List<CustomerFeedback> getCustomerFeedbacks() {
		return this.customerFeedbacks;
	}

	public void setCustomerFeedbacks(List<CustomerFeedback> customerFeedbacks) {
		this.customerFeedbacks = customerFeedbacks;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public List<DeliveryTimeSlot> getDeliveryTimeSlots() {
		return this.deliveryTimeSlots;
	}

	public void setDeliveryTimeSlots(List<DeliveryTimeSlot> deliveryTimeSlots) {
		this.deliveryTimeSlots = deliveryTimeSlots;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public List<ProductOffer> getProductOffers() {
		return this.productOffers;
	}

	public void setProductOffers(List<ProductOffer> productOffers) {
		this.productOffers = productOffers;
	}

}
