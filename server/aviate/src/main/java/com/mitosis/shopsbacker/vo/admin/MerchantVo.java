package com.mitosis.shopsbacker.vo.admin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.shopsbacker.vo.common.ImageVo;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class MerchantVo {
	private String merchantId;
	private String name;
	private UserVo user;
	private ImageVo logo;
	
	public String getMerchantId() {
		return merchantId;
	}
	public String getName() {
		return name;
	}
	public UserVo getUser() {
		return user;
	}
	public ImageVo getLogo() {
		return logo;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUser(UserVo user) {
		this.user = user;
	}
	public void setLogo(ImageVo logo) {
		this.logo = logo;
	}
	
	
}
