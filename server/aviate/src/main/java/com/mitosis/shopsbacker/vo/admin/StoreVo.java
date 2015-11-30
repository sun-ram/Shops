package com.mitosis.shopsbacker.vo.admin;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *  Created by Prabakaran.15/11/2015
 *  Reviewed by Sundaram  27/11/2015
 */
@XmlRootElement
public class StoreVo {
	
	private String storeId;
	private UserVo user;
	private MerchantVo merchant=new MerchantVo();
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
