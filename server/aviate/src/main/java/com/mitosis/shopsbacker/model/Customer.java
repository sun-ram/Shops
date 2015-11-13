package com.mitosis.shopsbacker.model;

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
	private List<CustomerFeedback> customerFeedbacksForCreatedby = new ArrayList<CustomerFeedback>();
	private List<MyCart> myCartsForCreatedby = new ArrayList<MyCart>();
	private List<Favourite> favouritesForUpdatedby = new ArrayList<Favourite>();
	private List<SalesOrder> salesOrders = new ArrayList<SalesOrder>();
	private List<CustomerFeedback> customerFeedbacksForCustomerId = new ArrayList<CustomerFeedback>();
	private List<Favourite> favouritesForCustomerId = new ArrayList<Favourite>();
	private List<Favourite> favouritesForCreatedby = new ArrayList<Favourite>();
	private List<CustomerFeedback> customerFeedbacksForUpdatedby = new ArrayList<CustomerFeedback>();
	private List<MissingProduct> missingProducts = new ArrayList<MissingProduct>();
	private List<Customer> customersForUpdatedby = new ArrayList<Customer>();
	private List<Address> addresses = new ArrayList<Address>();
	private List<Customer> customersForCreatedby = new ArrayList<Customer>();
	private List<MyCart> myCartsForUpdatedby = new ArrayList<MyCart>();
	private List<MyCart> myCartsForCustomerId = new ArrayList<MyCart>();

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
			char isactive, List<CustomerFeedback> customerFeedbacksForCreatedby,
			List<MyCart> myCartsForCreatedby, List<Favourite> favouritesForUpdatedby,
			List<SalesOrder> salesOrders, List<CustomerFeedback> customerFeedbacksForCustomerId,
			List<Favourite> favouritesForCustomerId, List<Favourite> favouritesForCreatedby,
			List<CustomerFeedback> customerFeedbacksForUpdatedby, List<MissingProduct> missingProducts,
			List<Customer> customersForUpdatedby, List<Address> addresses,
			List<Customer> customersForCreatedby, List<MyCart> myCartsForUpdatedby,
			List<MyCart> myCartsForCustomerId) {
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
	public List<CustomerFeedback> getCustomerFeedbacksForCreatedby() {
		return this.customerFeedbacksForCreatedby;
	}

	public void setCustomerFeedbacksForCreatedby(
			List<CustomerFeedback> customerFeedbacksForCreatedby) {
		this.customerFeedbacksForCreatedby = customerFeedbacksForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerByCreatedby")
	public List<MyCart> getMyCartsForCreatedby() {
		return this.myCartsForCreatedby;
	}

	public void setMyCartsForCreatedby(List<MyCart> myCartsForCreatedby) {
		this.myCartsForCreatedby = myCartsForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerByUpdatedby")
	public List<Favourite> getFavouritesForUpdatedby() {
		return this.favouritesForUpdatedby;
	}

	public void setFavouritesForUpdatedby(List<Favourite> favouritesForUpdatedby) {
		this.favouritesForUpdatedby = favouritesForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public List<SalesOrder> getSalesOrders() {
		return this.salesOrders;
	}

	public void setSalesOrders(List<SalesOrder> salesOrders) {
		this.salesOrders = salesOrders;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerByCustomerId")
	public List<CustomerFeedback> getCustomerFeedbacksForCustomerId() {
		return this.customerFeedbacksForCustomerId;
	}

	public void setCustomerFeedbacksForCustomerId(
			List<CustomerFeedback> customerFeedbacksForCustomerId) {
		this.customerFeedbacksForCustomerId = customerFeedbacksForCustomerId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerByCustomerId")
	public List<Favourite> getFavouritesForCustomerId() {
		return this.favouritesForCustomerId;
	}

	public void setFavouritesForCustomerId(List<Favourite> favouritesForCustomerId) {
		this.favouritesForCustomerId = favouritesForCustomerId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerByCreatedby")
	public List<Favourite> getFavouritesForCreatedby() {
		return this.favouritesForCreatedby;
	}

	public void setFavouritesForCreatedby(List<Favourite> favouritesForCreatedby) {
		this.favouritesForCreatedby = favouritesForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerByUpdatedby")
	public List<CustomerFeedback> getCustomerFeedbacksForUpdatedby() {
		return this.customerFeedbacksForUpdatedby;
	}

	public void setCustomerFeedbacksForUpdatedby(
			List<CustomerFeedback> customerFeedbacksForUpdatedby) {
		this.customerFeedbacksForUpdatedby = customerFeedbacksForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public List<MissingProduct> getMissingProducts() {
		return this.missingProducts;
	}

	public void setMissingProducts(List<MissingProduct> missingProducts) {
		this.missingProducts = missingProducts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerByUpdatedby")
	public List<Customer> getCustomersForUpdatedby() {
		return this.customersForUpdatedby;
	}

	public void setCustomersForUpdatedby(List<Customer> customersForUpdatedby) {
		this.customersForUpdatedby = customersForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public List<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerByCreatedby")
	public List<Customer> getCustomersForCreatedby() {
		return this.customersForCreatedby;
	}

	public void setCustomersForCreatedby(List<Customer> customersForCreatedby) {
		this.customersForCreatedby = customersForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerByUpdatedby")
	public List<MyCart> getMyCartsForUpdatedby() {
		return this.myCartsForUpdatedby;
	}

	public void setMyCartsForUpdatedby(List<MyCart> myCartsForUpdatedby) {
		this.myCartsForUpdatedby = myCartsForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerByCustomerId")
	public List<MyCart> getMyCartsForCustomerId() {
		return this.myCartsForCustomerId;
	}

	public void setMyCartsForCustomerId(List<MyCart> myCartsForCustomerId) {
		this.myCartsForCustomerId = myCartsForCustomerId;
	}

}
