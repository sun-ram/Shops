package com.mitosis.shopsbacker.model;

// Generated Nov 12, 2015 6:16:19 PM 

import java.util.Date;
import java.util.HashSet;
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

/**
 * Customer Created by Sundaram C.
 */
@Entity
@Table(name = "customer", catalog = "shopsbacker", uniqueConstraints = @UniqueConstraint(columnNames = "EMAIL"))
public class Customer implements java.io.Serializable {

	private String customerId;
	private Customer customerByCreatedby;
	private Customer customerByUpdatedby;
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
	private Set customerFeedbacksForCreatedby = new HashSet(0);
	private Set myCartsForCreatedby = new HashSet(0);
	private Set favouritesForUpdatedby = new HashSet(0);
	private Set salesOrders = new HashSet(0);
	private Set customerFeedbacksForCustomerId = new HashSet(0);
	private Set favouritesForCustomerId = new HashSet(0);
	private Set favouritesForCreatedby = new HashSet(0);
	private Set customerFeedbacksForUpdatedby = new HashSet(0);
	private Set missingProducts = new HashSet(0);
	private Set customersForUpdatedby = new HashSet(0);
	private Set addresses = new HashSet(0);
	private Set customersForCreatedby = new HashSet(0);
	private Set myCartsForUpdatedby = new HashSet(0);
	private Set myCartsForCustomerId = new HashSet(0);

	public Customer() {
	}

	public Customer(String customerId, Customer customerByCreatedby,
			Customer customerByUpdatedby, String name, String email,
			String password, Date created, Date updated, char isactive) {
		this.customerId = customerId;
		this.customerByCreatedby = customerByCreatedby;
		this.customerByUpdatedby = customerByUpdatedby;
		this.name = name;
		this.email = email;
		this.password = password;
		this.created = created;
		this.updated = updated;
		this.isactive = isactive;
	}

	public Customer(String customerId, Customer customerByCreatedby,
			Customer customerByUpdatedby, String name, String email,
			String password, String imageId, String deviceid,
			String deviceType, Integer phoneNo, Date created, Date updated,
			char isactive, Set customerFeedbacksForCreatedby,
			Set myCartsForCreatedby, Set favouritesForUpdatedby,
			Set salesOrders, Set customerFeedbacksForCustomerId,
			Set favouritesForCustomerId, Set favouritesForCreatedby,
			Set customerFeedbacksForUpdatedby, Set missingProducts,
			Set customersForUpdatedby, Set addresses,
			Set customersForCreatedby, Set myCartsForUpdatedby,
			Set myCartsForCustomerId) {
		this.customerId = customerId;
		this.customerByCreatedby = customerByCreatedby;
		this.customerByUpdatedby = customerByUpdatedby;
		this.name = name;
		this.email = email;
		this.password = password;
		this.imageId = imageId;
		this.deviceid = deviceid;
		this.deviceType = deviceType;
		this.phoneNo = phoneNo;
		this.created = created;
		this.updated = updated;
		this.isactive = isactive;
		this.customerFeedbacksForCreatedby = customerFeedbacksForCreatedby;
		this.myCartsForCreatedby = myCartsForCreatedby;
		this.favouritesForUpdatedby = favouritesForUpdatedby;
		this.salesOrders = salesOrders;
		this.customerFeedbacksForCustomerId = customerFeedbacksForCustomerId;
		this.favouritesForCustomerId = favouritesForCustomerId;
		this.favouritesForCreatedby = favouritesForCreatedby;
		this.customerFeedbacksForUpdatedby = customerFeedbacksForUpdatedby;
		this.missingProducts = missingProducts;
		this.customersForUpdatedby = customersForUpdatedby;
		this.addresses = addresses;
		this.customersForCreatedby = customersForCreatedby;
		this.myCartsForUpdatedby = myCartsForUpdatedby;
		this.myCartsForCustomerId = myCartsForCustomerId;
	}

	@Id
	@Column(name = "CUSTOMER_ID", unique = true, nullable = false, length = 32)
	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATEDBY", nullable = false)
	public Customer getCustomerByCreatedby() {
		return this.customerByCreatedby;
	}

	public void setCustomerByCreatedby(Customer customerByCreatedby) {
		this.customerByCreatedby = customerByCreatedby;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UPDATEDBY", nullable = false)
	public Customer getCustomerByUpdatedby() {
		return this.customerByUpdatedby;
	}

	public void setCustomerByUpdatedby(Customer customerByUpdatedby) {
		this.customerByUpdatedby = customerByUpdatedby;
	}

	@Column(name = "NAME", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "EMAIL", unique = true, nullable = false, length = 45)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "PASSWORD", nullable = false, length = 45)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "IMAGE_ID", length = 32)
	public String getImageId() {
		return this.imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	@Column(name = "DEVICEID", length = 45)
	public String getDeviceid() {
		return this.deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	@Column(name = "DEVICE_TYPE", length = 45)
	public String getDeviceType() {
		return this.deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	@Column(name = "PHONE_NO")
	public Integer getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(Integer phoneNo) {
		this.phoneNo = phoneNo;
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

	@Column(name = "ISACTIVE", nullable = false, length = 1)
	public char getIsactive() {
		return this.isactive;
	}

	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerByCreatedby")
	public Set getCustomerFeedbacksForCreatedby() {
		return this.customerFeedbacksForCreatedby;
	}

	public void setCustomerFeedbacksForCreatedby(
			Set customerFeedbacksForCreatedby) {
		this.customerFeedbacksForCreatedby = customerFeedbacksForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerByCreatedby")
	public Set getMyCartsForCreatedby() {
		return this.myCartsForCreatedby;
	}

	public void setMyCartsForCreatedby(Set myCartsForCreatedby) {
		this.myCartsForCreatedby = myCartsForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerByUpdatedby")
	public Set getFavouritesForUpdatedby() {
		return this.favouritesForUpdatedby;
	}

	public void setFavouritesForUpdatedby(Set favouritesForUpdatedby) {
		this.favouritesForUpdatedby = favouritesForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public Set getSalesOrders() {
		return this.salesOrders;
	}

	public void setSalesOrders(Set salesOrders) {
		this.salesOrders = salesOrders;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerByCustomerId")
	public Set getCustomerFeedbacksForCustomerId() {
		return this.customerFeedbacksForCustomerId;
	}

	public void setCustomerFeedbacksForCustomerId(
			Set customerFeedbacksForCustomerId) {
		this.customerFeedbacksForCustomerId = customerFeedbacksForCustomerId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerByCustomerId")
	public Set getFavouritesForCustomerId() {
		return this.favouritesForCustomerId;
	}

	public void setFavouritesForCustomerId(Set favouritesForCustomerId) {
		this.favouritesForCustomerId = favouritesForCustomerId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerByCreatedby")
	public Set getFavouritesForCreatedby() {
		return this.favouritesForCreatedby;
	}

	public void setFavouritesForCreatedby(Set favouritesForCreatedby) {
		this.favouritesForCreatedby = favouritesForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerByUpdatedby")
	public Set getCustomerFeedbacksForUpdatedby() {
		return this.customerFeedbacksForUpdatedby;
	}

	public void setCustomerFeedbacksForUpdatedby(
			Set customerFeedbacksForUpdatedby) {
		this.customerFeedbacksForUpdatedby = customerFeedbacksForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public Set getMissingProducts() {
		return this.missingProducts;
	}

	public void setMissingProducts(Set missingProducts) {
		this.missingProducts = missingProducts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerByUpdatedby")
	public Set getCustomersForUpdatedby() {
		return this.customersForUpdatedby;
	}

	public void setCustomersForUpdatedby(Set customersForUpdatedby) {
		this.customersForUpdatedby = customersForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public Set getAddresses() {
		return this.addresses;
	}

	public void setAddresses(Set addresses) {
		this.addresses = addresses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerByCreatedby")
	public Set getCustomersForCreatedby() {
		return this.customersForCreatedby;
	}

	public void setCustomersForCreatedby(Set customersForCreatedby) {
		this.customersForCreatedby = customersForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerByUpdatedby")
	public Set getMyCartsForUpdatedby() {
		return this.myCartsForUpdatedby;
	}

	public void setMyCartsForUpdatedby(Set myCartsForUpdatedby) {
		this.myCartsForUpdatedby = myCartsForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerByCustomerId")
	public Set getMyCartsForCustomerId() {
		return this.myCartsForCustomerId;
	}

	public void setMyCartsForCustomerId(Set myCartsForCustomerId) {
		this.myCartsForCustomerId = myCartsForCustomerId;
	}

}
