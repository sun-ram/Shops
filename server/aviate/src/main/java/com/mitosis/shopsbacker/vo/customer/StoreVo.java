package com.mitosis.shopsbacker.vo.customer;

// Generated Nov 12, 2015 6:16:19 PM 

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mitosis.shopsbacker.vo.admin.MerchantVo;

/**
 * Store Created by Sundaram C.
 */

public class StoreVo implements java.io.Serializable {

	private String storeId;
	private UserVo userByUpdatedby;
	private UserVo userByCreatedby;
	private UserVo userByUserId;
	private MerchantVo merchant;
	private String name;
	private char isactive;
	private Date created;
	private Date updated;
	private List<WarehouseVo> warehouses = new ArrayList<WarehouseVo>();
	private List<OrderNumberVo> orderNumbers = new ArrayList<OrderNumberVo>();
	private List<ProductCategoryVo> productCategories = new ArrayList<ProductCategoryVo>();
	private List<FavouriteVo> favourites = new ArrayList<FavouriteVo>();
	private List<MissingProductVo> missingProducts = new ArrayList<MissingProductVo>();
	private List<StoragebinVo> storagebins = new ArrayList<StoragebinVo>();
	private List<BannerVo> banners = new ArrayList<BannerVo>();
	private List<SalesOrderVo> salesOrders = new ArrayList<SalesOrderVo>();
	private List<CustomerFeedbackVo> customerFeedbacks = new ArrayList<CustomerFeedbackVo>();
	private List<MyCartVo> myCarts = new ArrayList<MyCartVo>();
	private List<UserVo> users = new ArrayList<UserVo>();
	private List<MovementVo> movements = new ArrayList<MovementVo>();
	private List<ProductTypeVo> productTypes = new ArrayList<ProductTypeVo>();
	private List<ProductInventoryVo> productInventories = new ArrayList<ProductInventoryVo>();
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public UserVo getUserByUpdatedby() {
		return userByUpdatedby;
	}
	public void setUserByUpdatedby(UserVo userByUpdatedby) {
		this.userByUpdatedby = userByUpdatedby;
	}
	public UserVo getUserByCreatedby() {
		return userByCreatedby;
	}
	public void setUserByCreatedby(UserVo userByCreatedby) {
		this.userByCreatedby = userByCreatedby;
	}
	public UserVo getUserByUserId() {
		return userByUserId;
	}
	public void setUserByUserId(UserVo userByUserId) {
		this.userByUserId = userByUserId;
	}
	public MerchantVo getMerchant() {
		return merchant;
	}
	public void setMerchant(MerchantVo merchant) {
		this.merchant = merchant;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public List<WarehouseVo> getWarehouses() {
		return warehouses;
	}
	public void setWarehouses(List<WarehouseVo> warehouses) {
		this.warehouses = warehouses;
	}
	public List<OrderNumberVo> getOrderNumbers() {
		return orderNumbers;
	}
	public void setOrderNumbers(List<OrderNumberVo> orderNumbers) {
		this.orderNumbers = orderNumbers;
	}
	public List<ProductCategoryVo> getProductCategories() {
		return productCategories;
	}
	public void setProductCategories(List<ProductCategoryVo> productCategories) {
		this.productCategories = productCategories;
	}
	public List<FavouriteVo> getFavourites() {
		return favourites;
	}
	public void setFavourites(List<FavouriteVo> favourites) {
		this.favourites = favourites;
	}
	public List<MissingProductVo> getMissingProducts() {
		return missingProducts;
	}
	public void setMissingProducts(List<MissingProductVo> missingProducts) {
		this.missingProducts = missingProducts;
	}
	public List<StoragebinVo> getStoragebins() {
		return storagebins;
	}
	public void setStoragebins(List<StoragebinVo> storagebins) {
		this.storagebins = storagebins;
	}
	public List<BannerVo> getBanners() {
		return banners;
	}
	public void setBanners(List<BannerVo> banners) {
		this.banners = banners;
	}
	public List<SalesOrderVo> getSalesOrders() {
		return salesOrders;
	}
	public void setSalesOrders(List<SalesOrderVo> salesOrders) {
		this.salesOrders = salesOrders;
	}
	public List<CustomerFeedbackVo> getCustomerFeedbacks() {
		return customerFeedbacks;
	}
	public void setCustomerFeedbacks(List<CustomerFeedbackVo> customerFeedbacks) {
		this.customerFeedbacks = customerFeedbacks;
	}
	public List<MyCartVo> getMyCarts() {
		return myCarts;
	}
	public void setMyCarts(List<MyCartVo> myCarts) {
		this.myCarts = myCarts;
	}
	public List<UserVo> getUsers() {
		return users;
	}
	public void setUsers(List<UserVo> users) {
		this.users = users;
	}
	public List<MovementVo> getMovements() {
		return movements;
	}
	public void setMovements(List<MovementVo> movements) {
		this.movements = movements;
	}
	public List<ProductTypeVo> getProductTypes() {
		return productTypes;
	}
	public void setProductTypes(List<ProductTypeVo> productTypes) {
		this.productTypes = productTypes;
	}
	public List<ProductInventoryVo> getProductInventories() {
		return productInventories;
	}
	public void setProductInventories(List<ProductInventoryVo> productInventories) {
		this.productInventories = productInventories;
	}

	
}
