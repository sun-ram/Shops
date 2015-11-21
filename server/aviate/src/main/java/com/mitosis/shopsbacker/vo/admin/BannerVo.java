package com.mitosis.shopsbacker.vo.admin;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.shopsbacker.model.Image;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.vo.common.ImageVo;

@XmlRootElement
public class BannerVo {

	private String bannerId;
	private String updatedby;
	private String createdby;
	private ImageVo image;
	private MerchantVo merchant;
	private StoreVo store;
	private String tabTitleSmall;
	private String tabTitleBold;
	private char isShopsbackerBanner;
	private char isactive;
	private Date created;
	
	public String getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	
	public StoreVo getStore() {
		return store;
	}
	public void setStore(StoreVo store) {
		this.store = store;
	}
	public String getTabTitleSmall() {
		return tabTitleSmall;
	}
	public void setTabTitleSmall(String tabTitleSmall) {
		this.tabTitleSmall = tabTitleSmall;
	}
	public String getTabTitleBold() {
		return tabTitleBold;
	}
	public void setTabTitleBold(String tabTitleBold) {
		this.tabTitleBold = tabTitleBold;
	}
	public char getIsShopsbackerBanner() {
		return isShopsbackerBanner;
	}
	public void setIsShopsbackerBanner(char isShopsbackerBanner) {
		this.isShopsbackerBanner = isShopsbackerBanner;
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
	private Date updated;
	
	public String getBannerId() {
		return bannerId;
	}
	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}
	
	public ImageVo getImage() {
		return image;
	}
	public void setImage(ImageVo image) {
		this.image = image;
	}
	public MerchantVo getMerchant() {
		return merchant;
	}
	public void setMerchant(MerchantVo merchant) {
		this.merchant = merchant;
	}

}
