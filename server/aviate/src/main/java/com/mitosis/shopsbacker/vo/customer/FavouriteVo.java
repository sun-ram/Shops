package com.mitosis.shopsbacker.vo.customer;

public class FavouriteVo {
	
	private String favouriteId;
	private String salesOrderId;
	private String merchantId;
	private String storeId;
	private String customerId;
	private String name;
	
	public String getSalesOrderId() {
		return salesOrderId;
	}
	public void setSalesOrderId(String salesOrderId) {
		this.salesOrderId = salesOrderId;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFavouriteId() {
		return favouriteId;
	}
	public void setFavouriteId(String favouriteId) {
		this.favouriteId = favouriteId;
	}
	
}
