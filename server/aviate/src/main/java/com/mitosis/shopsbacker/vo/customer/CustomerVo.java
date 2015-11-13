package com.mitosis.shopsbacker.vo.customer;

// Generated Nov 12, 2015 6:16:19 PM 

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.shopsbacker.vo.admin.AddressVo;

/**
 * Customer Created by Sundaram C.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CustomerVo implements java.io.Serializable {

	private String customerId;
	private CustomerVo customerByCreatedby;
	private CustomerVo customerByUpdatedby;
	private String name;
	private String email;
	private String password;
	private String imageId;
	private String deviceid;
	private String deviceType;
	private Integer phoneNo;
	private Date created;
	private Date updated;
	private char isactive;
	private List<CustomerFeedbackVo> customerFeedbacksForCreatedby = new ArrayList<CustomerFeedbackVo>();
	private List<MyCartVo> myCartsForCreatedby = new ArrayList<MyCartVo>();
	private List<FavouriteVo> favouritesForUpdatedby = new ArrayList<FavouriteVo>();
	private List<SalesOrderVo> salesOrders = new ArrayList<SalesOrderVo>();
	private List<CustomerFeedbackVo> customerFeedbacksForCustomerId = new ArrayList<CustomerFeedbackVo>();
	private List<FavouriteVo> favouritesForCustomerId = new ArrayList<FavouriteVo>();
	private List<FavouriteVo> favouritesForCreatedby = new ArrayList<FavouriteVo>();
	private List<CustomerFeedbackVo> customerFeedbacksForUpdatedby = new ArrayList<CustomerFeedbackVo>();
	private List<MissingProductVo> missingProducts = new ArrayList<MissingProductVo>();
	private List<CustomerVo> customersForUpdatedby = new ArrayList<CustomerVo>();
	private List<AddressVo> addresses = new ArrayList<AddressVo>();
	private List<CustomerVo> customersForCreatedby = new ArrayList<CustomerVo>();
	private List<MyCartVo> myCartsForUpdatedby = new ArrayList<MyCartVo>();
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public CustomerVo getCustomerByCreatedby() {
		return customerByCreatedby;
	}
	public void setCustomerByCreatedby(CustomerVo customerByCreatedby) {
		this.customerByCreatedby = customerByCreatedby;
	}
	public CustomerVo getCustomerByUpdatedby() {
		return customerByUpdatedby;
	}
	public void setCustomerByUpdatedby(CustomerVo customerByUpdatedby) {
		this.customerByUpdatedby = customerByUpdatedby;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public Integer getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(Integer phoneNo) {
		this.phoneNo = phoneNo;
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
	public char getIsactive() {
		return isactive;
	}
	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}
	public List<CustomerFeedbackVo> getCustomerFeedbacksForCreatedby() {
		return customerFeedbacksForCreatedby;
	}
	public void setCustomerFeedbacksForCreatedby(
			List<CustomerFeedbackVo> customerFeedbacksForCreatedby) {
		this.customerFeedbacksForCreatedby = customerFeedbacksForCreatedby;
	}
	public List<MyCartVo> getMyCartsForCreatedby() {
		return myCartsForCreatedby;
	}
	public void setMyCartsForCreatedby(List<MyCartVo> myCartsForCreatedby) {
		this.myCartsForCreatedby = myCartsForCreatedby;
	}
	public List<FavouriteVo> getFavouritesForUpdatedby() {
		return favouritesForUpdatedby;
	}
	public void setFavouritesForUpdatedby(List<FavouriteVo> favouritesForUpdatedby) {
		this.favouritesForUpdatedby = favouritesForUpdatedby;
	}
	public List<SalesOrderVo> getSalesOrders() {
		return salesOrders;
	}
	public void setSalesOrders(List<SalesOrderVo> salesOrders) {
		this.salesOrders = salesOrders;
	}
	public List<CustomerFeedbackVo> getCustomerFeedbacksForCustomerId() {
		return customerFeedbacksForCustomerId;
	}
	public void setCustomerFeedbacksForCustomerId(
			List<CustomerFeedbackVo> customerFeedbacksForCustomerId) {
		this.customerFeedbacksForCustomerId = customerFeedbacksForCustomerId;
	}
	public List<FavouriteVo> getFavouritesForCustomerId() {
		return favouritesForCustomerId;
	}
	public void setFavouritesForCustomerId(List<FavouriteVo> favouritesForCustomerId) {
		this.favouritesForCustomerId = favouritesForCustomerId;
	}
	public List<FavouriteVo> getFavouritesForCreatedby() {
		return favouritesForCreatedby;
	}
	public void setFavouritesForCreatedby(List<FavouriteVo> favouritesForCreatedby) {
		this.favouritesForCreatedby = favouritesForCreatedby;
	}
	public List<CustomerFeedbackVo> getCustomerFeedbacksForUpdatedby() {
		return customerFeedbacksForUpdatedby;
	}
	public void setCustomerFeedbacksForUpdatedby(
			List<CustomerFeedbackVo> customerFeedbacksForUpdatedby) {
		this.customerFeedbacksForUpdatedby = customerFeedbacksForUpdatedby;
	}
	public List<MissingProductVo> getMissingProducts() {
		return missingProducts;
	}
	public void setMissingProducts(List<MissingProductVo> missingProducts) {
		this.missingProducts = missingProducts;
	}
	public List<CustomerVo> getCustomersForUpdatedby() {
		return customersForUpdatedby;
	}
	public void setCustomersForUpdatedby(List<CustomerVo> customersForUpdatedby) {
		this.customersForUpdatedby = customersForUpdatedby;
	}
	public List<AddressVo> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<AddressVo> addresses) {
		this.addresses = addresses;
	}
	public List<CustomerVo> getCustomersForCreatedby() {
		return customersForCreatedby;
	}
	public void setCustomersForCreatedby(List<CustomerVo> customersForCreatedby) {
		this.customersForCreatedby = customersForCreatedby;
	}
	public List<MyCartVo> getMyCartsForUpdatedby() {
		return myCartsForUpdatedby;
	}
	public void setMyCartsForUpdatedby(List<MyCartVo> myCartsForUpdatedby) {
		this.myCartsForUpdatedby = myCartsForUpdatedby;
	}
	public List<MyCartVo> getMyCartsForCustomerId() {
		return myCartsForCustomerId;
	}
	public void setMyCartsForCustomerId(List<MyCartVo> myCartsForCustomerId) {
		this.myCartsForCustomerId = myCartsForCustomerId;
	}
	private List<MyCartVo> myCartsForCustomerId = new ArrayList<MyCartVo>();

	

}
