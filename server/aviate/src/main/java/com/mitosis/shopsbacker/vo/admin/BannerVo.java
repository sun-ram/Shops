package com.mitosis.shopsbacker.vo.admin;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.shopsbacker.vo.common.ImageVo;

/**
 * @author kathir
 *
 */
@XmlRootElement
public class BannerVo {

	private String bannerId;
	private ImageVo image;
	private MerchantVo merchant;
	private StoreVo store;
	private String tabTitleSmall;
	private String tabTitleBold;
	private String userId;
	
	private char isShopsbackerBanner;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
