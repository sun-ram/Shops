package com.mitosis.shopsbacker.vo.admin;

// Generated Nov 12, 2015 6:16:19 PM 

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mitosis.shopsbacker.vo.common.AddressVo;
import com.mitosis.shopsbacker.vo.common.ImageVo;
import com.mitosis.shopsbacker.vo.customer.RoleVo;

/**
 * User Created by Sundaram C.
 */
@XmlRootElement
@JsonInclude(Include.NON_EMPTY)
public class UserVo  {

	private String userId;
	private ImageVo image;
	private AddressVo address;
	private String name;
	private String password;
	private String emailid;
	private String deviceId;
	private String deviceType;
	private String userName;
	private String phoneNo;
	private RoleVo role;
	private MerchantVo merchant;
	private StoreVo store;
	private String generate;
	public String getGenerate() {
		return generate;
	}
	public void setGenerate(String generate) {
		this.generate = generate;
	}
	
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getUserId() {
		return userId;
	}
	public ImageVo getImage() {
		return image;
	}
	public AddressVo getAddress() {
		return address;
	}
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	public String getEmailid() {
		return emailid;
	}
	public String getUserName() {
		return userName;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setImage(ImageVo image) {
		this.image = image;
	}
	public void setAddress(AddressVo address) {
		this.address = address;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public RoleVo getRole() {
		return role;
	}
	public void setRole(RoleVo role) {
		this.role = role;
	}
	public MerchantVo getMerchant() {
		return merchant;
	}
	public void setMerchant(MerchantVo merchant) {
		this.merchant = merchant;
	}
	public StoreVo getStore() {
		return store;
	}
	public void setStore(StoreVo store) {
		this.store = store;
	}
	
	
	
}
