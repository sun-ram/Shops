package com.mitosis.shopsbacker.vo.customer;

// Generated Nov 12, 2015 6:16:19 PM 

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.shopsbacker.vo.admin.AddressVo;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;

/**
 * User Created by Sundaram C.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class UserVo implements java.io.Serializable {

	private String userId;
	private UserVo userByUpdatedby;
	private UserVo userByCreatedby;
	private ImageVo image;
	private RoleVo role;
	private MerchantVo merchant;
	private AddressVo address;
	private StoreVo store;
	private String name;
	private String password;
	private String emailid;
	private String deveiceid;
	private String userName;
	private Integer phoneNo;
	private char isactive;
	private Date created;
	private Date updated;
	private List<StoreVo> storesForUserId = new ArrayList<StoreVo>();
	private List<SalesOrderVo> salesOrdersForShopperId = new ArrayList<SalesOrderVo>();
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public UserVo getUserByUpdatedby() {
		return userByUpdatedby;
	}
	public void setUserByUpdatedby(UserVo userByUpdatedby) {
		this.userByUpdatedby = userByUpdatedby;
	}
	public UserVo getUserByCreatedby() {
		return userByCreatedby;
	}
	public void setUserByCreatedby(UserVo userByCreatedby) {
		this.userByCreatedby = userByCreatedby;
	}
	public ImageVo getImage() {
		return image;
	}
	public void setImage(ImageVo image) {
		this.image = image;
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
	public AddressVo getAddress() {
		return address;
	}
	public void setAddress(AddressVo address) {
		this.address = address;
	}
	public StoreVo getStore() {
		return store;
	}
	public void setStore(StoreVo store) {
		this.store = store;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getDeveiceid() {
		return deveiceid;
	}
	public void setDeveiceid(String deveiceid) {
		this.deveiceid = deveiceid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(Integer phoneNo) {
		this.phoneNo = phoneNo;
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
	public List<StoreVo> getStoresForUserId() {
		return storesForUserId;
	}
	public void setStoresForUserId(List<StoreVo> storesForUserId) {
		this.storesForUserId = storesForUserId;
	}
	public List<SalesOrderVo> getSalesOrdersForShopperId() {
		return salesOrdersForShopperId;
	}
	public void setSalesOrdersForShopperId(
			List<SalesOrderVo> salesOrdersForShopperId) {
		this.salesOrdersForShopperId = salesOrdersForShopperId;
	}

	
}
