package com.mitosis.shopsbacker.vo.admin;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StoreVo {
	
	private String storeId;
	private UserVo user;
	private MerchantVo merchant;
	private String name;
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public UserVo getUser() {
		return user;
	}
	public void setUser(UserVo user) {
		this.user = user;
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

}
