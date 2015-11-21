package com.mitosis.shopsbacker.model;

// Generated Nov 12, 2015 6:16:19 PM 

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * Banner Created by Sundaram C.
 */
@Entity
@Table(name = "banner", catalog = "shopsbacker")
public class Banner implements java.io.Serializable {

	private String bannerId;
	private String updatedby;
	private String createdby;
	private Image image;
	private Merchant merchant;
	private Store store;
	private String tabTitleSmall;
	private String tabTitleBold;
	private char isShopsbackerBanner;
	private char isactive;
	private Date created;
	private Date updated;

	public Banner() {
	}

	public Banner(String bannerId, char isShopsbackerBanner, char isactive) {
		this.bannerId = bannerId;
		this.isShopsbackerBanner = isShopsbackerBanner;
		this.isactive = isactive;
	}

	public Banner(String bannerId, String updatedby, String createdby,
			Image image, Merchant merchant, Store store, String tabTitleSmall,
			String tabTitleBold, char isShopsbackerBanner, char isactive,
			Date created, Date updated) {
		this.bannerId = bannerId;
		this.updatedby = updatedby;
		this.createdby = createdby;
		this.image = image;
		this.merchant = merchant;
		this.store = store;
		this.tabTitleSmall = tabTitleSmall;
		this.tabTitleBold = tabTitleBold;
		this.isShopsbackerBanner = isShopsbackerBanner;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "BANNER_ID", unique = true, nullable = false, length = 32)
	public String getBannerId() {
		return this.bannerId;
	}

	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}

	public String getUpdatedby() {
		return this.updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public String getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "IMAGE_ID")
	public Image getImage() {
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MERCHANT_ID")
	public Merchant getMerchant() {
		return this.merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STORE_ID")
	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
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

	@Column(name = "IS_SHOPSBACKER_BANNER", nullable = false, length = 1)
	public char getIsShopsbackerBanner() {
		return this.isShopsbackerBanner;
	}

	public void setIsShopsbackerBanner(char isShopsbackerBanner) {
		this.isShopsbackerBanner = isShopsbackerBanner;
	}

	@Column(name = "ISACTIVE", nullable = false, length = 1)
	public char getIsactive() {
		return this.isactive;
	}

	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED", length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED", length = 19)
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}
