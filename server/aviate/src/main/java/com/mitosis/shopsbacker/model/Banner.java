package com.mitosis.shopsbacker.model;

// Generated Nov 6, 2015 7:27:52 PM 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Banner Created by Sundaram C.
 */
@Entity
@Table(name = "banner", catalog = "shopsbacker")
public class Banner implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String bannerId;
	private String imageId;
	private String merchantId;
	private String tabTitleSmall;
	private String tabTitleBold;
	private String storeId;
	private char isShopsbackerBanner;

	public Banner() {
	}

	public Banner(String bannerId, char isShopsbackerBanner) {
		this.bannerId = bannerId;
		this.isShopsbackerBanner = isShopsbackerBanner;
	}

	public Banner(String bannerId, String imageId, String merchantId,
			String tabTitleSmall, String tabTitleBold, String storeId,
			char isShopsbackerBanner) {
		this.bannerId = bannerId;
		this.imageId = imageId;
		this.merchantId = merchantId;
		this.tabTitleSmall = tabTitleSmall;
		this.tabTitleBold = tabTitleBold;
		this.storeId = storeId;
		this.isShopsbackerBanner = isShopsbackerBanner;
	}

	@Id
	@Column(name = "BANNER_ID", unique = true, nullable = false, length = 32)
	public String getBannerId() {
		return this.bannerId;
	}

	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}

	@Column(name = "IMAGE_ID", length = 32)
	public String getImageId() {
		return this.imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	@Column(name = "MERCHANT_ID", length = 32)
	public String getMerchantId() {
		return this.merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	@Column(name = "TAB_TITLE_SMALL", length = 100)
	public String getTabTitleSmall() {
		return this.tabTitleSmall;
	}

	public void setTabTitleSmall(String tabTitleSmall) {
		this.tabTitleSmall = tabTitleSmall;
	}

	@Column(name = "TAB_TITLE_BOLD", length = 100)
	public String getTabTitleBold() {
		return this.tabTitleBold;
	}

	public void setTabTitleBold(String tabTitleBold) {
		this.tabTitleBold = tabTitleBold;
	}

	@Column(name = "STORE_ID", length = 32)
	public String getStoreId() {
		return this.storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	@Column(name = "IS_SHOPSBACKER_BANNER", nullable = false, length = 1)
	public char getIsShopsbackerBanner() {
		return this.isShopsbackerBanner;
	}

	public void setIsShopsbackerBanner(char isShopsbackerBanner) {
		this.isShopsbackerBanner = isShopsbackerBanner;
	}

}
