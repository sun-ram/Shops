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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

/**
 * Customer Created by Sundaram C.
 */
@Entity
@Table(name = "customer", uniqueConstraints = @UniqueConstraint(columnNames = "EMAIL"))
public class Customer implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String customerId;
	private String createdby;
	private String updatedby;
	private String name;
	private String email;
	private String password;
	private String imageId;
	private String deviceid;
	private String deviceType;
	private String phoneNo;
	private Date created;
	private Date updated;
	private char isactive;
	private List<SalesOrder> salesOrders = new ArrayList<SalesOrder>();
	private List<CustomerFeedback> customerFeedbacksForCustomerId = new ArrayList<CustomerFeedback>();
	private List<Favourite> favouritesForCustomerId = new ArrayList<Favourite>();
	private List<MissingProduct> missingProducts = new ArrayList<MissingProduct>();
	private List<Address> addresses = new ArrayList<Address>();
	private List<MyCart> myCartsForCustomerId = new ArrayList<MyCart>();

	public Customer() {
	}

	public Customer(String customerId, String createdby, String updatedby,
			String name, String email, String password, Date created,
			Date updated, char isactive) {
		this.customerId = customerId;
		this.createdby = createdby;
		this.updatedby = updatedby;
		this.name = name;
		this.email = email;
		this.password = password;
		this.created = created;
		this.updated = updated;
		this.isactive = isactive;
	}

	public Customer(String customerId, String createdby, String updatedby,
			String name, String email, String password, String imageId,
			String deviceid, String deviceType, String phoneNo, Date created,
			Date updated, char isactive,
			List<CustomerFeedback> customerFeedbacksForCreatedby,
			List<MyCart> myCartsForCreatedby,
			List<Favourite> favouritesForUpdatedby,
			List<SalesOrder> salesOrders,
			List<CustomerFeedback> customerFeedbacksForCustomerId,
			List<Favourite> favouritesForCustomerId,
			List<Favourite> favouritesForCreatedby,
			List<CustomerFeedback> customerFeedbacksForUpdatedby,
			List<MissingProduct> missingProducts,
			List<Customer> customersForUpdatedby, List<Address> addresses,
			List<Customer> customersForCreatedby,
			List<MyCart> myCartsForUpdatedby, List<MyCart> myCartsForCustomerId) {
		this.customerId = customerId;
		this.createdby = createdby;
		this.updatedby = updatedby;
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
		this.salesOrders = salesOrders;
		this.customerFeedbacksForCustomerId = customerFeedbacksForCustomerId;
		this.favouritesForCustomerId = favouritesForCustomerId;
		this.missingProducts = missingProducts;
		this.addresses = addresses;
		this.myCartsForCustomerId = myCartsForCustomerId;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "CUSTOMER_ID", unique = true, nullable = false, length = 32)
	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Column(name = "UPDATEDBY", length = 32)
	public String getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
	
	@Column(name = "CREATEDBY", length = 32)
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	
	@Column(name = "NAME", nullable = true, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "EMAIL", unique = true, nullable = true, length = 45)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "PASSWORD", nullable = false, length = 100)
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

	@Column(name = "PHONE_NO", unique = true, nullable = true)
	public String getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public List<SalesOrder> getSalesOrders() {
		return this.salesOrders;
	}

	public void setSalesOrders(List<SalesOrder> salesOrders) {
		this.salesOrders = salesOrders;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public List<CustomerFeedback> getCustomerFeedbacksForCustomerId() {
		return this.customerFeedbacksForCustomerId;
	}

	public void setCustomerFeedbacksForCustomerId(
			List<CustomerFeedback> customerFeedbacksForCustomerId) {
		this.customerFeedbacksForCustomerId = customerFeedbacksForCustomerId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public List<Favourite> getFavouritesForCustomerId() {
		return this.favouritesForCustomerId;
	}

	public void setFavouritesForCustomerId(
			List<Favourite> favouritesForCustomerId) {
		this.favouritesForCustomerId = favouritesForCustomerId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public List<MissingProduct> getMissingProducts() {
		return this.missingProducts;
	}

	public void setMissingProducts(List<MissingProduct> missingProducts) {
		this.missingProducts = missingProducts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public List<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public List<MyCart> getMyCartsForCustomerId() {
		return this.myCartsForCustomerId;
	}

	public void setMyCartsForCustomerId(List<MyCart> myCartsForCustomerId) {
		this.myCartsForCustomerId = myCartsForCustomerId;
	}

}
