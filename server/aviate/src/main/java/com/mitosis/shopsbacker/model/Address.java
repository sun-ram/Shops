package com.mitosis.shopsbacker.model;

// Generated Nov 12, 2015 6:16:19 PM 

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * Address Created by Sundaram C.
 */
@Entity
@Table(name = "address", catalog = "shopsbacker")
public class Address implements java.io.Serializable {

	private String addressId;
	private State state;
	private Customer customer;
	private Country country;
	private String landmark;
	private String address1;
	private String address2;
	private String city;
	private String pinCode;
	private String phoneNo;
	private String latitude;
	private String longitude;
	private Character isactive;
	private String createdby;
	private String updatedby;
	private Date created;
	private Date updated;
	private List<SalesOrder> salesOrders = new ArrayList<SalesOrder>();
	private List<User>  users =new ArrayList<User>();
	private List<MerchantRequest>  merchantRequests = new ArrayList<MerchantRequest>();

	public Address() {
	}

	public Address(String addressId, State state, Country country,
			String address1, String pinCode, String createdby,
			String updatedby, Date created, Date updated) {
		this.addressId = addressId;
		this.state = state;
		this.country = country;
		this.address1 = address1;
		this.pinCode = pinCode;
		this.createdby = createdby;
		this.updatedby = updatedby;
		this.created = created;
		this.updated = updated;
	}

	public Address(String addressId, State state, Customer customer,
			Country country, String landmark, String address1, String address2,
			String city, String pinCode, String phoneNo, String latitude,
			String longitude, Character isactive, String createdby,
			String updatedby, Date created, Date updated, List<SalesOrder> salesOrders,
			List<User> users, List<MerchantRequest> merchantRequests) {
		this.addressId = addressId;
		this.state = state;
		this.customer = customer;
		this.country = country;
		this.landmark = landmark;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.pinCode = pinCode;
		this.phoneNo = phoneNo;
		this.latitude = latitude;
		this.longitude = longitude;
		this.isactive = isactive;
		this.createdby = createdby;
		this.updatedby = updatedby;
		this.created = created;
		this.updated = updated;
		this.salesOrders = salesOrders;
		this.users = users;
		this.merchantRequests = merchantRequests;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ADDRESS_ID", unique = true, nullable = false, length = 32)
	public String getAddressId() {
		return this.addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STATE_ID", nullable = false)
	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID")
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COUNTRY_ID", nullable = false)
	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Column(name = "LANDMARK", length = 150)
	public String getLandmark() {
		return this.landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	@Column(name = "ADDRESS1", nullable = false, length = 150)
	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	@Column(name = "ADDRESS2", length = 150)
	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	@Column(name = "CITY", length = 45)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "PIN_CODE", nullable = false, length = 12)
	public String getPinCode() {
		return this.pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	@Column(name = "PHONE_NO", length = 45)
	public String getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Column(name = "LATITUDE", length = 45)
	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Column(name = "LONGITUDE", length = 45)
	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Column(name = "ISACTIVE", length = 1)
	public Character getIsactive() {
		return this.isactive;
	}

	public void setIsactive(Character isactive) {
		this.isactive = isactive;
	}

	@Column(name = "CREATEDBY", nullable = false, length = 32)
	public String getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	@Column(name = "UPDATEDBY", nullable = false, length = 32)
	public String getUpdatedby() {
		return this.updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED", nullable = false, length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED", nullable = false, length = 19)
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "address")
	public List<SalesOrder> getSalesOrders() {
		return this.salesOrders;
	}

	public void setSalesOrders(List<SalesOrder> salesOrders) {
		this.salesOrders = salesOrders;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "address")
	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "address")
	public List<MerchantRequest> getMerchantRequests() {
		return this.merchantRequests;
	}

	public void setMerchantRequests(List<MerchantRequest> merchantRequests) {
		this.merchantRequests = merchantRequests;
	}

}
