package com.mitosis.shopsbacker.vo.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.shopsbacker.model.Banner;
import com.mitosis.shopsbacker.model.CustomerFeedback;
import com.mitosis.shopsbacker.model.DeliveryTimeSlot;
import com.mitosis.shopsbacker.model.Discount;
import com.mitosis.shopsbacker.model.Favourite;
import com.mitosis.shopsbacker.model.MissingProduct;
import com.mitosis.shopsbacker.model.Movement;
import com.mitosis.shopsbacker.model.MyCart;
import com.mitosis.shopsbacker.model.OrderNumber;
import com.mitosis.shopsbacker.model.OrderTax;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.model.ProductInventory;
import com.mitosis.shopsbacker.model.ProductOffer;
import com.mitosis.shopsbacker.model.ProductType;
import com.mitosis.shopsbacker.model.ShippingCharges;
import com.mitosis.shopsbacker.model.Storagebin;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.Tax;
import com.mitosis.shopsbacker.model.User;
import com.mitosis.shopsbacker.model.Warehouse;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class MerchantVo {
	private String merchantId;
	private String Updatedby;
	private String Createdby;
	private String name;
	private User userId;
	private String logo;
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
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getUpdatedby() {
		return Updatedby;
	}
	public void setUpdatedby(String updatedby) {
		Updatedby = updatedby;
	}
	public String getCreatedby() {
		return Createdby;
	}
	public void setCreatedby(String createdby) {
		Createdby = createdby;
	}
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public char getIsactive() {
		return isactive;
	}
	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public List<ProductCategory> getProductCategories() {
		return productCategories;
	}
	public void setProductCategories(List<ProductCategory> productCategories) {
		this.productCategories = productCategories;
	}
	public List<Favourite> getFavourites() {
		return favourites;
	}
	public void setFavourites(List<Favourite> favourites) {
		this.favourites = favourites;
	}
	public List<ProductType> getProductTypes() {
		return productTypes;
	}
	public void setProductTypes(List<ProductType> productTypes) {
		this.productTypes = productTypes;
	}
	public List<Banner> getBanners() {
		return banners;
	}
	public void setBanners(List<Banner> banners) {
		this.banners = banners;
	}
	public List<Discount> getDiscounts() {
		return discounts;
	}
	public void setDiscounts(List<Discount> discounts) {
		this.discounts = discounts;
	}
	public List<Store> getStores() {
		return stores;
	}
	public void setStores(List<Store> stores) {
		this.stores = stores;
	}
	public List<Tax> getTaxes() {
		return taxes;
	}
	public void setTaxes(List<Tax> taxes) {
		this.taxes = taxes;
	}
	public List<Warehouse> getWarehouses() {
		return warehouses;
	}
	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}
	public List<ShippingCharges> getShippingChargeses() {
		return shippingChargeses;
	}
	public void setShippingChargeses(List<ShippingCharges> shippingChargeses) {
		this.shippingChargeses = shippingChargeses;
	}
	public List<MyCart> getMyCarts() {
		return myCarts;
	}
	public void setMyCarts(List<MyCart> myCarts) {
		this.myCarts = myCarts;
	}
	public List<OrderTax> getOrderTaxes() {
		return orderTaxes;
	}
	public void setOrderTaxes(List<OrderTax> orderTaxes) {
		this.orderTaxes = orderTaxes;
	}
	public List<Storagebin> getStoragebins() {
		return storagebins;
	}
	public void setStoragebins(List<Storagebin> storagebins) {
		this.storagebins = storagebins;
	}
	public List<OrderNumber> getOrderNumbers() {
		return orderNumbers;
	}
	public void setOrderNumbers(List<OrderNumber> orderNumbers) {
		this.orderNumbers = orderNumbers;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<ProductInventory> getProductInventories() {
		return productInventories;
	}
	public void setProductInventories(List<ProductInventory> productInventories) {
		this.productInventories = productInventories;
	}
	public List<Movement> getMovements() {
		return movements;
	}
	public void setMovements(List<Movement> movements) {
		this.movements = movements;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public List<MissingProduct> getMissingProducts() {
		return missingProducts;
	}
	public void setMissingProducts(List<MissingProduct> missingProducts) {
		this.missingProducts = missingProducts;
	}
	public List<CustomerFeedback> getCustomerFeedbacks() {
		return customerFeedbacks;
	}
	public void setCustomerFeedbacks(List<CustomerFeedback> customerFeedbacks) {
		this.customerFeedbacks = customerFeedbacks;
	}
	public List<DeliveryTimeSlot> getDeliveryTimeSlots() {
		return deliveryTimeSlots;
	}
	public void setDeliveryTimeSlots(List<DeliveryTimeSlot> deliveryTimeSlots) {
		this.deliveryTimeSlots = deliveryTimeSlots;
	}
	public List<ProductOffer> getProductOffers() {
		return productOffers;
	}
	public void setProductOffers(List<ProductOffer> productOffers) {
		this.productOffers = productOffers;
	}
	

	
	
}
