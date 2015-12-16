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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

/**
 * Merchant Created by Sundaram C.
 */
@Entity
@Table(name = "merchant", uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
public class Merchant implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String merchantId;
	private String updatedby;
	private String createdby;
	private String name;
	private User user;
	private Image logo;
	private char isactive;
	private Date created;
	private Date updated;
	private List<ProductCategory> productCategories = new ArrayList<ProductCategory>();
	private List<Favourite> favourites = new ArrayList<Favourite>();
	private List<ProductType> productTypes = new ArrayList<ProductType>();
	private List<Banner> banners = new ArrayList<Banner>();
	private List<Discount> discounts = new ArrayList<Discount>();
	private List<Store> stores = new ArrayList<Store>();
	private List<Tax> taxes = new ArrayList<Tax>();
	private List<Warehouse> warehouses = new ArrayList<Warehouse>();
	private List<ShippingCharges> shippingChargeses = new ArrayList<ShippingCharges>();
	private List<MyCart> myCarts = new ArrayList<MyCart>();
	private List<OrderTax> orderTaxes = new ArrayList<OrderTax>();
	private List<Storagebin> storagebins = new ArrayList<Storagebin>();
	private List<OrderNumber> orderNumbers = new ArrayList<OrderNumber>();
	private List<User> users = new ArrayList<User>();
	private List<ProductInventory> productInventories = new ArrayList<ProductInventory>();
	private List<Movement> movements = new ArrayList<Movement>();
	private List<Product> products = new ArrayList<Product>();
	private List<MissingProduct> missingProducts = new ArrayList<MissingProduct>();
	private List<CustomerFeedback> customerFeedbacks = new ArrayList<CustomerFeedback>();
	private List<DeliveryTimeSlot> deliveryTimeSlots = new ArrayList<DeliveryTimeSlot>();
	private List<ProductOffer> productOffers = new ArrayList<ProductOffer>();
	private List<StoreHoliday> storeHolidays = new ArrayList<StoreHoliday>();

	public Merchant() {
	}

	public Merchant(String merchantId, String updatedby, String createdby,
			String name, char isactive, Date created, Date updated) {
		this.merchantId = merchantId;
		this.updatedby = updatedby;
		this.createdby = createdby;
		this.name = name;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	public Merchant(String merchantId, String updatedby, String createdby,
			String name, User user, Image logo, char isactive, Date created,
			Date updated, List<ProductCategory> productCategories,
			List<Favourite> favourites, List<ProductType> productTypes,
			List<Banner> banners, List<Discount> discounts, List<Store> stores,
			List<Tax> taxes, List<Warehouse> warehouses,
			List<ShippingCharges> shippingChargeses, List<MyCart> myCarts,
			List<OrderTax> orderTaxes, List<Storagebin> storagebins,
			List<OrderNumber> orderNumbers, List<User> users,
			List<ProductInventory> productInventories,
			List<Movement> movements, List<Product> products,
			List<MissingProduct> missingProducts,
			List<CustomerFeedback> customerFeedbacks,
			List<DeliveryTimeSlot> deliveryTimeSlots,
			List<ProductOffer> productOffers, List<StoreHoliday> storeHolidays) {
		this.merchantId = merchantId;
		this.updatedby = updatedby;
		this.createdby = createdby;
		this.name = name;
		this.user = user;
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
		this.storeHolidays = storeHolidays;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "MERCHANT_ID", unique = true, nullable = false, length = 32)
	public String getMerchantId() {
		return this.merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
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

	@Column(name = "NAME", unique = true, nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "LOGO_ID")
	public Image getLogo() {
		return this.logo;
	}

	public void setLogo(Image logo) {
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
	public List<Favourite> getFavourites() {
		return this.favourites;
	}

	public void setFavourites(List<Favourite> favourites) {
		this.favourites = favourites;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public List<ProductType> getProductTypes() {
		return this.productTypes;
	}

	public void setProductTypes(List<ProductType> productTypes) {
		this.productTypes = productTypes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public List<Banner> getBanners() {
		return this.banners;
	}

	public void setBanners(List<Banner> banners) {
		this.banners = banners;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public List<Discount> getDiscounts() {
		return this.discounts;
	}

	public void setDiscounts(List<Discount> discounts) {
		this.discounts = discounts;
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
	public List<ShippingCharges> getShippingChargeses() {
		return this.shippingChargeses;
	}

	public void setShippingChargeses(List<ShippingCharges> shippingChargeses) {
		this.shippingChargeses = shippingChargeses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public List<MyCart> getMyCarts() {
		return this.myCarts;
	}

	public void setMyCarts(List<MyCart> myCarts) {
		this.myCarts = myCarts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public List<OrderTax> getOrderTaxes() {
		return this.orderTaxes;
	}

	public void setOrderTaxes(List<OrderTax> orderTaxes) {
		this.orderTaxes = orderTaxes;
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
	public List<MissingProduct> getMissingProducts() {
		return this.missingProducts;
	}

	public void setMissingProducts(List<MissingProduct> missingProducts) {
		this.missingProducts = missingProducts;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	public List<StoreHoliday> getStoreHolidays() {
		return storeHolidays;
	}

	public void setStoreHolidays(List<StoreHoliday> storeHolidays) {
		this.storeHolidays = storeHolidays;
	}

}
