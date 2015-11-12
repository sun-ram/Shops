package com.mitosis.shopsbacker.model;

// Generated Nov 12, 2015 3:32:23 PM 

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
 * User Created by Sundaram C.
 */
@Entity
@Table(name = "user", catalog = "shopsbacker", uniqueConstraints = @UniqueConstraint(columnNames = "USER_NAME"))
public class User implements java.io.Serializable {

	private String userId;
	private User userByUpdatedby;
	private User userByCreatedby;
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
	private Integer phoneNo;
	private char isactive;
	private Date created;
	private Date updated;
	private Set productCategoriesForCreatedby = new HashSet(0);
	private Set taxesForCreatedby = new HashSet(0);
	private Set bannersForCreatedby = new HashSet(0);
	private Set uomsForCreatedby = new HashSet(0);
	private Set storesForCreatedby = new HashSet(0);
	private Set productOffersForUpdatedby = new HashSet(0);
	private Set bannersForUpdatedby = new HashSet(0);
	private Set warehousesForCreatedby = new HashSet(0);
	private Set storagebinsForCreatedby = new HashSet(0);
	private Set rolesForUpdatedby = new HashSet(0);
	private Set movementsForUpdatedby = new HashSet(0);
	private Set productInventoriesForUpdatedby = new HashSet(0);
	private Set storesForUpdatedby = new HashSet(0);
	private Set discountsForUpdatedby = new HashSet(0);
	private Set orderNumbersForCreatedby = new HashSet(0);
	private Set deliveryTimeSlotsForUpdatedby = new HashSet(0);
	private Set productTypesForCreatedby = new HashSet(0);
	private Set productInventoriesForCreatedby = new HashSet(0);
	private Set salesOrdersForBackerId = new HashSet(0);
	private Set storesForUserId = new HashSet(0);
	private Set deliveryTimeSlotsForCreatedby = new HashSet(0);
	private Set productOffersForCreatedby = new HashSet(0);
	private Set usersForCreatedby = new HashSet(0);
	private Set productImagesForUpdatedby = new HashSet(0);
	private Set productImagesForCreatedby = new HashSet(0);
	private Set rolesForCreatedby = new HashSet(0);
	private Set movementsForCreatedby = new HashSet(0);
	private Set productsForUpdatedby = new HashSet(0);
	private Set merchantsForCreatedby = new HashSet(0);
	private Set warehousesForUpdatedby = new HashSet(0);
	private Set salesOrdersForShopperId = new HashSet(0);
	private Set taxesForUpdatedby = new HashSet(0);
	private Set shippingChargesesForCreatedby = new HashSet(0);
	private Set storagebinsForUpdatedby = new HashSet(0);
	private Set discountsForCreatedby = new HashSet(0);
	private Set productTypesForUpdatedby = new HashSet(0);
	private Set usersForUpdatedby = new HashSet(0);
	private Set merchantsForUpdatedby = new HashSet(0);
	private Set uomsForUpdatedby = new HashSet(0);
	private Set orderNumbersForUpdatedby = new HashSet(0);
	private Set shippingChargesesForUpdatedby = new HashSet(0);
	private Set productsForCreatedby = new HashSet(0);
	private Set productCategoriesForUpdatedby = new HashSet(0);

	public User() {
	}

	public User(String userId, User userByUpdatedby, User userByCreatedby,
			String name, String password, String emailid, String userName,
			char isactive, Date created, Date updated) {
		this.userId = userId;
		this.userByUpdatedby = userByUpdatedby;
		this.userByCreatedby = userByCreatedby;
		this.name = name;
		this.password = password;
		this.emailid = emailid;
		this.userName = userName;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	public User(String userId, User userByUpdatedby, User userByCreatedby,
			Image image, Role role, Merchant merchant, Address address,
			Store store, String name, String password, String emailid,
			String deveiceid, String userName, Integer phoneNo, char isactive,
			Date created, Date updated, Set productCategoriesForCreatedby,
			Set taxesForCreatedby, Set bannersForCreatedby,
			Set uomsForCreatedby, Set storesForCreatedby,
			Set productOffersForUpdatedby, Set bannersForUpdatedby,
			Set warehousesForCreatedby, Set storagebinsForCreatedby,
			Set rolesForUpdatedby, Set movementsForUpdatedby,
			Set productInventoriesForUpdatedby, Set storesForUpdatedby,
			Set discountsForUpdatedby, Set orderNumbersForCreatedby,
			Set deliveryTimeSlotsForUpdatedby, Set productTypesForCreatedby,
			Set productInventoriesForCreatedby, Set salesOrdersForBackerId,
			Set storesForUserId, Set deliveryTimeSlotsForCreatedby,
			Set productOffersForCreatedby, Set usersForCreatedby,
			Set productImagesForUpdatedby, Set productImagesForCreatedby,
			Set rolesForCreatedby, Set movementsForCreatedby,
			Set productsForUpdatedby, Set merchantsForCreatedby,
			Set warehousesForUpdatedby, Set salesOrdersForShopperId,
			Set taxesForUpdatedby, Set shippingChargesesForCreatedby,
			Set storagebinsForUpdatedby, Set discountsForCreatedby,
			Set productTypesForUpdatedby, Set usersForUpdatedby,
			Set merchantsForUpdatedby, Set uomsForUpdatedby,
			Set orderNumbersForUpdatedby, Set shippingChargesesForUpdatedby,
			Set productsForCreatedby, Set productCategoriesForUpdatedby) {
		this.userId = userId;
		this.userByUpdatedby = userByUpdatedby;
		this.userByCreatedby = userByCreatedby;
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
		this.productCategoriesForCreatedby = productCategoriesForCreatedby;
		this.taxesForCreatedby = taxesForCreatedby;
		this.bannersForCreatedby = bannersForCreatedby;
		this.uomsForCreatedby = uomsForCreatedby;
		this.storesForCreatedby = storesForCreatedby;
		this.productOffersForUpdatedby = productOffersForUpdatedby;
		this.bannersForUpdatedby = bannersForUpdatedby;
		this.warehousesForCreatedby = warehousesForCreatedby;
		this.storagebinsForCreatedby = storagebinsForCreatedby;
		this.rolesForUpdatedby = rolesForUpdatedby;
		this.movementsForUpdatedby = movementsForUpdatedby;
		this.productInventoriesForUpdatedby = productInventoriesForUpdatedby;
		this.storesForUpdatedby = storesForUpdatedby;
		this.discountsForUpdatedby = discountsForUpdatedby;
		this.orderNumbersForCreatedby = orderNumbersForCreatedby;
		this.deliveryTimeSlotsForUpdatedby = deliveryTimeSlotsForUpdatedby;
		this.productTypesForCreatedby = productTypesForCreatedby;
		this.productInventoriesForCreatedby = productInventoriesForCreatedby;
		this.salesOrdersForBackerId = salesOrdersForBackerId;
		this.storesForUserId = storesForUserId;
		this.deliveryTimeSlotsForCreatedby = deliveryTimeSlotsForCreatedby;
		this.productOffersForCreatedby = productOffersForCreatedby;
		this.usersForCreatedby = usersForCreatedby;
		this.productImagesForUpdatedby = productImagesForUpdatedby;
		this.productImagesForCreatedby = productImagesForCreatedby;
		this.rolesForCreatedby = rolesForCreatedby;
		this.movementsForCreatedby = movementsForCreatedby;
		this.productsForUpdatedby = productsForUpdatedby;
		this.merchantsForCreatedby = merchantsForCreatedby;
		this.warehousesForUpdatedby = warehousesForUpdatedby;
		this.salesOrdersForShopperId = salesOrdersForShopperId;
		this.taxesForUpdatedby = taxesForUpdatedby;
		this.shippingChargesesForCreatedby = shippingChargesesForCreatedby;
		this.storagebinsForUpdatedby = storagebinsForUpdatedby;
		this.discountsForCreatedby = discountsForCreatedby;
		this.productTypesForUpdatedby = productTypesForUpdatedby;
		this.usersForUpdatedby = usersForUpdatedby;
		this.merchantsForUpdatedby = merchantsForUpdatedby;
		this.uomsForUpdatedby = uomsForUpdatedby;
		this.orderNumbersForUpdatedby = orderNumbersForUpdatedby;
		this.shippingChargesesForUpdatedby = shippingChargesesForUpdatedby;
		this.productsForCreatedby = productsForCreatedby;
		this.productCategoriesForUpdatedby = productCategoriesForUpdatedby;
	}

	@Id
	@Column(name = "USER_ID", unique = true, nullable = false, length = 32)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UPDATEDBY", nullable = false)
	public User getUserByUpdatedby() {
		return this.userByUpdatedby;
	}

	public void setUserByUpdatedby(User userByUpdatedby) {
		this.userByUpdatedby = userByUpdatedby;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATEDBY", nullable = false)
	public User getUserByCreatedby() {
		return this.userByCreatedby;
	}

	public void setUserByCreatedby(User userByCreatedby) {
		this.userByCreatedby = userByCreatedby;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IMAGE_ID")
	public Image getImage() {
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID")
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MERCHANT_ID")
	public Merchant getMerchant() {
		return this.merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ADDRESS_ID")
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@ManyToOne(fetch = FetchType.LAZY)
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
	public Integer getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(Integer phoneNo) {
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public Set getProductCategoriesForCreatedby() {
		return this.productCategoriesForCreatedby;
	}

	public void setProductCategoriesForCreatedby(
			Set productCategoriesForCreatedby) {
		this.productCategoriesForCreatedby = productCategoriesForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public Set getTaxesForCreatedby() {
		return this.taxesForCreatedby;
	}

	public void setTaxesForCreatedby(Set taxesForCreatedby) {
		this.taxesForCreatedby = taxesForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public Set getBannersForCreatedby() {
		return this.bannersForCreatedby;
	}

	public void setBannersForCreatedby(Set bannersForCreatedby) {
		this.bannersForCreatedby = bannersForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public Set getUomsForCreatedby() {
		return this.uomsForCreatedby;
	}

	public void setUomsForCreatedby(Set uomsForCreatedby) {
		this.uomsForCreatedby = uomsForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public Set getStoresForCreatedby() {
		return this.storesForCreatedby;
	}

	public void setStoresForCreatedby(Set storesForCreatedby) {
		this.storesForCreatedby = storesForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUpdatedby")
	public Set getProductOffersForUpdatedby() {
		return this.productOffersForUpdatedby;
	}

	public void setProductOffersForUpdatedby(Set productOffersForUpdatedby) {
		this.productOffersForUpdatedby = productOffersForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUpdatedby")
	public Set getBannersForUpdatedby() {
		return this.bannersForUpdatedby;
	}

	public void setBannersForUpdatedby(Set bannersForUpdatedby) {
		this.bannersForUpdatedby = bannersForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public Set getWarehousesForCreatedby() {
		return this.warehousesForCreatedby;
	}

	public void setWarehousesForCreatedby(Set warehousesForCreatedby) {
		this.warehousesForCreatedby = warehousesForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public Set getStoragebinsForCreatedby() {
		return this.storagebinsForCreatedby;
	}

	public void setStoragebinsForCreatedby(Set storagebinsForCreatedby) {
		this.storagebinsForCreatedby = storagebinsForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUpdatedby")
	public Set getRolesForUpdatedby() {
		return this.rolesForUpdatedby;
	}

	public void setRolesForUpdatedby(Set rolesForUpdatedby) {
		this.rolesForUpdatedby = rolesForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUpdatedby")
	public Set getMovementsForUpdatedby() {
		return this.movementsForUpdatedby;
	}

	public void setMovementsForUpdatedby(Set movementsForUpdatedby) {
		this.movementsForUpdatedby = movementsForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUpdatedby")
	public Set getProductInventoriesForUpdatedby() {
		return this.productInventoriesForUpdatedby;
	}

	public void setProductInventoriesForUpdatedby(
			Set productInventoriesForUpdatedby) {
		this.productInventoriesForUpdatedby = productInventoriesForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUpdatedby")
	public Set getStoresForUpdatedby() {
		return this.storesForUpdatedby;
	}

	public void setStoresForUpdatedby(Set storesForUpdatedby) {
		this.storesForUpdatedby = storesForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUpdatedby")
	public Set getDiscountsForUpdatedby() {
		return this.discountsForUpdatedby;
	}

	public void setDiscountsForUpdatedby(Set discountsForUpdatedby) {
		this.discountsForUpdatedby = discountsForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public Set getOrderNumbersForCreatedby() {
		return this.orderNumbersForCreatedby;
	}

	public void setOrderNumbersForCreatedby(Set orderNumbersForCreatedby) {
		this.orderNumbersForCreatedby = orderNumbersForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUpdatedby")
	public Set getDeliveryTimeSlotsForUpdatedby() {
		return this.deliveryTimeSlotsForUpdatedby;
	}

	public void setDeliveryTimeSlotsForUpdatedby(
			Set deliveryTimeSlotsForUpdatedby) {
		this.deliveryTimeSlotsForUpdatedby = deliveryTimeSlotsForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public Set getProductTypesForCreatedby() {
		return this.productTypesForCreatedby;
	}

	public void setProductTypesForCreatedby(Set productTypesForCreatedby) {
		this.productTypesForCreatedby = productTypesForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public Set getProductInventoriesForCreatedby() {
		return this.productInventoriesForCreatedby;
	}

	public void setProductInventoriesForCreatedby(
			Set productInventoriesForCreatedby) {
		this.productInventoriesForCreatedby = productInventoriesForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByBackerId")
	public Set getSalesOrdersForBackerId() {
		return this.salesOrdersForBackerId;
	}

	public void setSalesOrdersForBackerId(Set salesOrdersForBackerId) {
		this.salesOrdersForBackerId = salesOrdersForBackerId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUserId")
	public Set getStoresForUserId() {
		return this.storesForUserId;
	}

	public void setStoresForUserId(Set storesForUserId) {
		this.storesForUserId = storesForUserId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public Set getDeliveryTimeSlotsForCreatedby() {
		return this.deliveryTimeSlotsForCreatedby;
	}

	public void setDeliveryTimeSlotsForCreatedby(
			Set deliveryTimeSlotsForCreatedby) {
		this.deliveryTimeSlotsForCreatedby = deliveryTimeSlotsForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public Set getProductOffersForCreatedby() {
		return this.productOffersForCreatedby;
	}

	public void setProductOffersForCreatedby(Set productOffersForCreatedby) {
		this.productOffersForCreatedby = productOffersForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public Set getUsersForCreatedby() {
		return this.usersForCreatedby;
	}

	public void setUsersForCreatedby(Set usersForCreatedby) {
		this.usersForCreatedby = usersForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUpdatedby")
	public Set getProductImagesForUpdatedby() {
		return this.productImagesForUpdatedby;
	}

	public void setProductImagesForUpdatedby(Set productImagesForUpdatedby) {
		this.productImagesForUpdatedby = productImagesForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public Set getProductImagesForCreatedby() {
		return this.productImagesForCreatedby;
	}

	public void setProductImagesForCreatedby(Set productImagesForCreatedby) {
		this.productImagesForCreatedby = productImagesForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public Set getRolesForCreatedby() {
		return this.rolesForCreatedby;
	}

	public void setRolesForCreatedby(Set rolesForCreatedby) {
		this.rolesForCreatedby = rolesForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public Set getMovementsForCreatedby() {
		return this.movementsForCreatedby;
	}

	public void setMovementsForCreatedby(Set movementsForCreatedby) {
		this.movementsForCreatedby = movementsForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUpdatedby")
	public Set getProductsForUpdatedby() {
		return this.productsForUpdatedby;
	}

	public void setProductsForUpdatedby(Set productsForUpdatedby) {
		this.productsForUpdatedby = productsForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public Set getMerchantsForCreatedby() {
		return this.merchantsForCreatedby;
	}

	public void setMerchantsForCreatedby(Set merchantsForCreatedby) {
		this.merchantsForCreatedby = merchantsForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUpdatedby")
	public Set getWarehousesForUpdatedby() {
		return this.warehousesForUpdatedby;
	}

	public void setWarehousesForUpdatedby(Set warehousesForUpdatedby) {
		this.warehousesForUpdatedby = warehousesForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByShopperId")
	public Set getSalesOrdersForShopperId() {
		return this.salesOrdersForShopperId;
	}

	public void setSalesOrdersForShopperId(Set salesOrdersForShopperId) {
		this.salesOrdersForShopperId = salesOrdersForShopperId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUpdatedby")
	public Set getTaxesForUpdatedby() {
		return this.taxesForUpdatedby;
	}

	public void setTaxesForUpdatedby(Set taxesForUpdatedby) {
		this.taxesForUpdatedby = taxesForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public Set getShippingChargesesForCreatedby() {
		return this.shippingChargesesForCreatedby;
	}

	public void setShippingChargesesForCreatedby(
			Set shippingChargesesForCreatedby) {
		this.shippingChargesesForCreatedby = shippingChargesesForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUpdatedby")
	public Set getStoragebinsForUpdatedby() {
		return this.storagebinsForUpdatedby;
	}

	public void setStoragebinsForUpdatedby(Set storagebinsForUpdatedby) {
		this.storagebinsForUpdatedby = storagebinsForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public Set getDiscountsForCreatedby() {
		return this.discountsForCreatedby;
	}

	public void setDiscountsForCreatedby(Set discountsForCreatedby) {
		this.discountsForCreatedby = discountsForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUpdatedby")
	public Set getProductTypesForUpdatedby() {
		return this.productTypesForUpdatedby;
	}

	public void setProductTypesForUpdatedby(Set productTypesForUpdatedby) {
		this.productTypesForUpdatedby = productTypesForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUpdatedby")
	public Set getUsersForUpdatedby() {
		return this.usersForUpdatedby;
	}

	public void setUsersForUpdatedby(Set usersForUpdatedby) {
		this.usersForUpdatedby = usersForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUpdatedby")
	public Set getMerchantsForUpdatedby() {
		return this.merchantsForUpdatedby;
	}

	public void setMerchantsForUpdatedby(Set merchantsForUpdatedby) {
		this.merchantsForUpdatedby = merchantsForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUpdatedby")
	public Set getUomsForUpdatedby() {
		return this.uomsForUpdatedby;
	}

	public void setUomsForUpdatedby(Set uomsForUpdatedby) {
		this.uomsForUpdatedby = uomsForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUpdatedby")
	public Set getOrderNumbersForUpdatedby() {
		return this.orderNumbersForUpdatedby;
	}

	public void setOrderNumbersForUpdatedby(Set orderNumbersForUpdatedby) {
		this.orderNumbersForUpdatedby = orderNumbersForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUpdatedby")
	public Set getShippingChargesesForUpdatedby() {
		return this.shippingChargesesForUpdatedby;
	}

	public void setShippingChargesesForUpdatedby(
			Set shippingChargesesForUpdatedby) {
		this.shippingChargesesForUpdatedby = shippingChargesesForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public Set getProductsForCreatedby() {
		return this.productsForCreatedby;
	}

	public void setProductsForCreatedby(Set productsForCreatedby) {
		this.productsForCreatedby = productsForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUpdatedby")
	public Set getProductCategoriesForUpdatedby() {
		return this.productCategoriesForUpdatedby;
	}

	public void setProductCategoriesForUpdatedby(
			Set productCategoriesForUpdatedby) {
		this.productCategoriesForUpdatedby = productCategoriesForUpdatedby;
	}

}
