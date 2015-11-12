package com.mitosis.shopsbacker.model;

// Generated Nov 6, 2015 7:27:52 PM 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Favourite Created by Sundaram C.
 */
@Entity
@Table(name = "favourite", catalog = "shopsbacker", uniqueConstraints = @UniqueConstraint(columnNames = {
		"NAME", "CUSTOMER_ID" }))
public class Favourite implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String favouriteId;
	private String name;
	private String orderId;
	private String customerId;
	private String merchantId;
	private String storeId;

	public Favourite() {
	}

	public Favourite(String favouriteId, String name, String orderId,
			String customerId, String merchantId, String storeId) {
		this.favouriteId = favouriteId;
		this.name = name;
		this.orderId = orderId;
		this.customerId = customerId;
		this.merchantId = merchantId;
		this.storeId = storeId;
	}

	@Id
	@Column(name = "FAVOURITE_ID", unique = true, nullable = false, length = 32)
	public String getFavouriteId() {
		return this.favouriteId;
	}

	public void setFavouriteId(String favouriteId) {
		this.favouriteId = favouriteId;
	}

	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ORDER_ID", nullable = false, length = 32)
	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name = "CUSTOMER_ID", nullable = false, length = 32)
	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Column(name = "MERCHANT_ID", nullable = false, length = 32)
	public String getMerchantId() {
		return this.merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	@Column(name = "STORE_ID", nullable = false, length = 32)
	public String getStoreId() {
		return this.storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

}
