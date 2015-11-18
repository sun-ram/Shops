package com.mitosis.shopsbacker.model;

// Generated Nov 12, 2015 6:16:19 PM 

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

/**
 * User Created by Sundaram C.
 */
@Entity
@Table(name = "user", catalog = "shopsbacker", uniqueConstraints = @UniqueConstraint(columnNames = "USER_NAME"))
public class User implements java.io.Serializable {

	private String userId;
	private String updatedby;
	private String createdby;
	private Image image;
	private Role role;
	private Merchant merchant;
	private Address address;
	private Store store;
	private String name;
	private String password;
	private String emailid;
	private String deveiceid;
	private String userName;
	private String phoneNo;
	private char isactive;
	private Date created;
	private Date updated;
	private Merchant merchantForUser;
	private List<SalesOrder> salesOrdersForBackerId = new ArrayList<SalesOrder>();
	private List<Store> storesForUserId = new ArrayList<Store>();
	private List<SalesOrder> salesOrdersForShopperId = new ArrayList<SalesOrder>();

	public User() {
	}

	public User(String userId, String updatedby, String createdby,
			String name, String password, String emailid, String userName,
			char isactive, Date created, Date updated) {
		this.userId = userId;
		this.updatedby = updatedby;
		this.createdby = createdby;
		this.name = name;
		this.password = password;
		this.emailid = emailid;
		this.userName = userName;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	public User(String userId, String updatedby, String createdby,
			Image image, Role role, Merchant merchant, Address address,
			Store store, String name, String password, String emailid,
			String deveiceid, String userName, String phoneNo, char isactive,
			Date created, Date updated, 
  List<SalesOrder> salesOrdersForBackerId,List<Store> storesForUserId,  
			List<SalesOrder> salesOrdersForShopperId, Merchant merchantForUser) {
		this.userId = userId;
		this.updatedby = updatedby;
		this.createdby = createdby;
		this.image = image;
		this.role = role;
		this.merchant = merchant;
		this.address = address;
		this.store = store;
		this.name = name;
		this.password = password;
		this.emailid = emailid;
		this.deveiceid = deveiceid;
		this.userName = userName;
		this.phoneNo = phoneNo;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
		this.salesOrdersForBackerId = salesOrdersForBackerId;
		this.storesForUserId = storesForUserId;
		this.salesOrdersForShopperId = salesOrdersForShopperId;
		this.merchantForUser = merchantForUser;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "USER_ID", unique = true, nullable = false, length = 32)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "UPDATEDBY", length = 32)
	public String getUpdatedby() {
		return this.updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	@Column(name = "CREATEDBY", length = 32)
	public String getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IMAGE_ID")
	public Image getImage() {
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ROLE_ID")
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MERCHANT_ID")
	public Merchant getMerchant() {
		return this.merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ADDRESS_ID")
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STORE_ID")
	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	@Column(name = "NAME", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PASSWORD", nullable = false, length = 45)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "EMAILID", nullable = false, length = 45)
	public String getEmailid() {
		return this.emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	@Column(name = "DEVEICEID", length = 45)
	public String getDeveiceid() {
		return this.deveiceid;
	}

	public void setDeveiceid(String deveiceid) {
		this.deveiceid = deveiceid;
	}

	@Column(name = "USER_NAME", unique = true, nullable = false, length = 45)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "PHONE_NO")
	public String getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Column(name = "ISACTIVE", nullable = false, length = 1)
	public char getIsactive() {
		return this.isactive;
	}

	public void setIsactive(char isactive) {
		this.isactive = isactive;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "backer")
	public List<SalesOrder> getSalesOrdersForBackerId() {
		return this.salesOrdersForBackerId;
	}

	public void setSalesOrdersForBackerId(List<SalesOrder> salesOrdersForBackerId) {
		this.salesOrdersForBackerId = salesOrdersForBackerId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public List<Store> getStoresForUserId() {
		return this.storesForUserId;
	}

	public void setStoresForUserId(List<Store> storesForUserId) {
		this.storesForUserId = storesForUserId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shopper")
	public List<SalesOrder> getSalesOrdersForShopperId() {
		return this.salesOrdersForShopperId;
	}

	public void setSalesOrdersForShopperId(List<SalesOrder> salesOrdersForShopperId) {
		this.salesOrdersForShopperId = salesOrdersForShopperId;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Merchant getMerchantForUser() {
		return merchantForUser;
	}

	public void setMerchantForUser(Merchant merchantForUser) {
		this.merchantForUser = merchantForUser;
	}
}
