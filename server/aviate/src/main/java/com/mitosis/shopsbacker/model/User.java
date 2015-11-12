package com.mitosis.shopsbacker.model;

// Generated Nov 6, 2015 7:27:52 PM 

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
 * User Created by Sundaram C.
 */
@Entity
@Table(name = "user", catalog = "shopsbacker", uniqueConstraints = @UniqueConstraint(columnNames = "USER_NAME"))
public class User implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	
	private List<ProductCategory> productCategoriesForCreatedby = new ArrayList<ProductCategory>();
	private List<Tax> taxesForCreatedby = new ArrayList<Tax>();
	private List<Uom> uomsForCreatedby = new ArrayList<Uom>();
	private List<Store> storesForCreatedby = new ArrayList<Store>();
	private List<ProductOffer> productOffersForUpdatedby = new ArrayList<ProductOffer>();
	
	private List<Warehouse> warehousesForCreatedby = new ArrayList<Warehouse>();
	private List<Storagebin> storagebinsForCreatedby = new ArrayList<Storagebin>();
	private List<Role> rolesForUpdatedby = new ArrayList<Role>();
	private List<Movement> movementsForUpdatedby = new ArrayList<Movement>();
	private List<ProductInventory> productInventoriesForUpdatedby = new ArrayList<ProductInventory>();
	
	private List<Store> storesForUpdatedby = new ArrayList<Store>();
	private List<OrderNumber> orderNumbersForCreatedby = new ArrayList<OrderNumber>();
	private List<DeliveryTimeSlot> deliveryTimeSlotsForUpdatedby = new ArrayList<DeliveryTimeSlot>();
	private List<ProductType> productTypesForCreatedby = new ArrayList<ProductType>();
	private List<ProductInventory> productInventoriesForCreatedby = new ArrayList<ProductInventory>();
	
	private List<SalesOrder> salesOrdersForBackerId = new ArrayList<SalesOrder>();
	private List<Store> storesForUserId = new ArrayList<Store>();
	private List<DeliveryTimeSlot> deliveryTimeSlotsForCreatedby = new ArrayList<DeliveryTimeSlot>();
	private List<ProductOffer> productOffersForCreatedby = new ArrayList<ProductOffer>();
	private List<User> usersForCreatedby = new ArrayList<User>();
	
	
	
	private Set movementlinesForCreatedby = new HashSet(0);
	private Set productImagesForUpdatedby = new HashSet(0);
	private Set productImagesForCreatedby = new HashSet(0);
	private Set rolesForCreatedby = new HashSet(0);
	private Set productsForUpdatedby = new HashSet(0);
	
	private Set movementsForCreatedby = new HashSet(0);
	private Set merchantsForCreatedby = new HashSet(0);
	private Set warehousesForUpdatedby = new HashSet(0);
	private Set salesOrdersForShopperId = new HashSet(0);
	private Set taxesForUpdatedby = new HashSet(0);
	
	private Set storagebinsForUpdatedby = new HashSet(0);
	private Set productOfferlinesForUpdatedby = new HashSet(0);
	private Set productTypesForUpdatedby = new HashSet(0);
	private Set usersForUpdatedby = new HashSet(0);
	private Set movementlinesForUpdatedby = new HashSet(0);
	
	private Set merchantsForUpdatedby = new HashSet(0);
	private Set uomsForUpdatedby = new HashSet(0);
	private Set productOfferlinesForCreatedby = new HashSet(0);
	private Set orderNumbersForUpdatedby = new HashSet(0);
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
			Date created, Date updated, List<ProductCategory> productCategoriesForCreatedby,
			List<Tax> taxesForCreatedby, List<Uom> uomsForCreatedby,
			List<Store> storesForCreatedby, List<ProductOffer> productOffersForUpdatedby,
			List<Warehouse> warehousesForCreatedby, List<Storagebin> storagebinsForCreatedby,
			List<Role> rolesForUpdatedby, List<Movement> movementsForUpdatedby,
			List<ProductInventory> productInventoriesForUpdatedby, List<Store> storesForUpdatedby,
			List<OrderNumber> orderNumbersForCreatedby, List<DeliveryTimeSlot> deliveryTimeSlotsForUpdatedby,
			List<ProductType> productTypesForCreatedby, List<ProductInventory> productInventoriesForCreatedby,
			List<SalesOrder> salesOrdersForBackerId, List<Store> storesForUserId,
			List<DeliveryTimeSlot> deliveryTimeSlotsForCreatedby, List<ProductOffer> productOffersForCreatedby,
			List<User> usersForCreatedby, Set movementlinesForCreatedby,
			Set productImagesForUpdatedby, Set productImagesForCreatedby,
			Set rolesForCreatedby, Set productsForUpdatedby,
			Set movementsForCreatedby, Set merchantsForCreatedby,
			Set warehousesForUpdatedby, Set salesOrdersForShopperId,
			Set taxesForUpdatedby, Set storagebinsForUpdatedby,
			Set productOfferlinesForUpdatedby, Set productTypesForUpdatedby,
			Set usersForUpdatedby, Set movementlinesForUpdatedby,
			Set merchantsForUpdatedby, Set uomsForUpdatedby,
			Set productOfferlinesForCreatedby, Set orderNumbersForUpdatedby,
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
		this.uomsForCreatedby = uomsForCreatedby;
		this.storesForCreatedby = storesForCreatedby;
		this.productOffersForUpdatedby = productOffersForUpdatedby;
		this.warehousesForCreatedby = warehousesForCreatedby;
		this.storagebinsForCreatedby = storagebinsForCreatedby;
		this.rolesForUpdatedby = rolesForUpdatedby;
		this.movementsForUpdatedby = movementsForUpdatedby;
		this.productInventoriesForUpdatedby = productInventoriesForUpdatedby;
		this.storesForUpdatedby = storesForUpdatedby;
		this.orderNumbersForCreatedby = orderNumbersForCreatedby;
		this.deliveryTimeSlotsForUpdatedby = deliveryTimeSlotsForUpdatedby;
		this.productTypesForCreatedby = productTypesForCreatedby;
		this.productInventoriesForCreatedby = productInventoriesForCreatedby;
		this.salesOrdersForBackerId = salesOrdersForBackerId;
		this.storesForUserId = storesForUserId;
		this.deliveryTimeSlotsForCreatedby = deliveryTimeSlotsForCreatedby;
		this.productOffersForCreatedby = productOffersForCreatedby;
		this.usersForCreatedby = usersForCreatedby;
		this.movementlinesForCreatedby = movementlinesForCreatedby;
		this.productImagesForUpdatedby = productImagesForUpdatedby;
		this.productImagesForCreatedby = productImagesForCreatedby;
		this.rolesForCreatedby = rolesForCreatedby;
		this.productsForUpdatedby = productsForUpdatedby;
		this.movementsForCreatedby = movementsForCreatedby;
		this.merchantsForCreatedby = merchantsForCreatedby;
		this.warehousesForUpdatedby = warehousesForUpdatedby;
		this.salesOrdersForShopperId = salesOrdersForShopperId;
		this.taxesForUpdatedby = taxesForUpdatedby;
		this.storagebinsForUpdatedby = storagebinsForUpdatedby;
		this.productOfferlinesForUpdatedby = productOfferlinesForUpdatedby;
		this.productTypesForUpdatedby = productTypesForUpdatedby;
		this.usersForUpdatedby = usersForUpdatedby;
		this.movementlinesForUpdatedby = movementlinesForUpdatedby;
		this.merchantsForUpdatedby = merchantsForUpdatedby;
		this.uomsForUpdatedby = uomsForUpdatedby;
		this.productOfferlinesForCreatedby = productOfferlinesForCreatedby;
		this.orderNumbersForUpdatedby = orderNumbersForUpdatedby;
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
	public List<ProductCategory> getProductCategoriesForCreatedby() {
		return this.productCategoriesForCreatedby;
	}

	public void setProductCategoriesForCreatedby(
			List<ProductCategory> productCategoriesForCreatedby) {
		this.productCategoriesForCreatedby = productCategoriesForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public List<Tax> getTaxesForCreatedby() {
		return this.taxesForCreatedby;
	}

	public void setTaxesForCreatedby(List<Tax> taxesForCreatedby) {
		this.taxesForCreatedby = taxesForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public List<Uom> getUomsForCreatedby() {
		return this.uomsForCreatedby;
	}

	public void setUomsForCreatedby(List<Uom> uomsForCreatedby) {
		this.uomsForCreatedby = uomsForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public List<Store> getStoresForCreatedby() {
		return this.storesForCreatedby;
	}

	public void setStoresForCreatedby(List<Store> storesForCreatedby) {
		this.storesForCreatedby = storesForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUpdatedby")
	public List<ProductOffer> getProductOffersForUpdatedby() {
		return this.productOffersForUpdatedby;
	}

	public void setProductOffersForUpdatedby(List<ProductOffer> productOffersForUpdatedby) {
		this.productOffersForUpdatedby = productOffersForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public List<Warehouse> getWarehousesForCreatedby() {
		return this.warehousesForCreatedby;
	}

	public void setWarehousesForCreatedby(List<Warehouse> warehousesForCreatedby) {
		this.warehousesForCreatedby = warehousesForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public List<Storagebin> getStoragebinsForCreatedby() {
		return this.storagebinsForCreatedby;
	}

	public void setStoragebinsForCreatedby(List<Storagebin> storagebinsForCreatedby) {
		this.storagebinsForCreatedby = storagebinsForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUpdatedby")
	public List<Role> getRolesForUpdatedby() {
		return this.rolesForUpdatedby;
	}

	public void setRolesForUpdatedby(List<Role> rolesForUpdatedby) {
		this.rolesForUpdatedby = rolesForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUpdatedby")
	public List<Movement> getMovementsForUpdatedby() {
		return this.movementsForUpdatedby;
	}

	public void setMovementsForUpdatedby(List<Movement> movementsForUpdatedby) {
		this.movementsForUpdatedby = movementsForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUpdatedby")
	public List<ProductInventory> getProductInventoriesForUpdatedby() {
		return this.productInventoriesForUpdatedby;
	}

	public void setProductInventoriesForUpdatedby(
			List<ProductInventory> productInventoriesForUpdatedby) {
		this.productInventoriesForUpdatedby = productInventoriesForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUpdatedby")
	public List<Store> getStoresForUpdatedby() {
		return this.storesForUpdatedby;
	}

	public void setStoresForUpdatedby(List<Store> storesForUpdatedby) {
		this.storesForUpdatedby = storesForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public List<OrderNumber> getOrderNumbersForCreatedby() {
		return this.orderNumbersForCreatedby;
	}

	public void setOrderNumbersForCreatedby(List<OrderNumber> orderNumbersForCreatedby) {
		this.orderNumbersForCreatedby = orderNumbersForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUpdatedby")
	public List<DeliveryTimeSlot> getDeliveryTimeSlotsForUpdatedby() {
		return this.deliveryTimeSlotsForUpdatedby;
	}

	public void setDeliveryTimeSlotsForUpdatedby(
			List<DeliveryTimeSlot> deliveryTimeSlotsForUpdatedby) {
		this.deliveryTimeSlotsForUpdatedby = deliveryTimeSlotsForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public List<ProductType> getProductTypesForCreatedby() {
		return this.productTypesForCreatedby;
	}

	public void setProductTypesForCreatedby(List<ProductType> productTypesForCreatedby) {
		this.productTypesForCreatedby = productTypesForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public List<ProductInventory> getProductInventoriesForCreatedby() {
		return this.productInventoriesForCreatedby;
	}

	public void setProductInventoriesForCreatedby(
			List<ProductInventory> productInventoriesForCreatedby) {
		this.productInventoriesForCreatedby = productInventoriesForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByBackerId")
	public List<SalesOrder> getSalesOrdersForBackerId() {
		return this.salesOrdersForBackerId;
	}

	public void setSalesOrdersForBackerId(List<SalesOrder> salesOrdersForBackerId) {
		this.salesOrdersForBackerId = salesOrdersForBackerId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUserId")
	public List<Store> getStoresForUserId() {
		return this.storesForUserId;
	}

	public void setStoresForUserId(List<Store> storesForUserId) {
		this.storesForUserId = storesForUserId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public List<DeliveryTimeSlot> getDeliveryTimeSlotsForCreatedby() {
		return this.deliveryTimeSlotsForCreatedby;
	}

	public void setDeliveryTimeSlotsForCreatedby(
			List<DeliveryTimeSlot> deliveryTimeSlotsForCreatedby) {
		this.deliveryTimeSlotsForCreatedby = deliveryTimeSlotsForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public List<ProductOffer> getProductOffersForCreatedby() {
		return this.productOffersForCreatedby;
	}

	public void setProductOffersForCreatedby(List<ProductOffer> productOffersForCreatedby) {
		this.productOffersForCreatedby = productOffersForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public List<User> getUsersForCreatedby() {
		return this.usersForCreatedby;
	}

	public void setUsersForCreatedby(List<User> usersForCreatedby) {
		this.usersForCreatedby = usersForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public Set getMovementlinesForCreatedby() {
		return this.movementlinesForCreatedby;
	}

	public void setMovementlinesForCreatedby(Set movementlinesForCreatedby) {
		this.movementlinesForCreatedby = movementlinesForCreatedby;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUpdatedby")
	public Set getProductsForUpdatedby() {
		return this.productsForUpdatedby;
	}

	public void setProductsForUpdatedby(Set productsForUpdatedby) {
		this.productsForUpdatedby = productsForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public Set getMovementsForCreatedby() {
		return this.movementsForCreatedby;
	}

	public void setMovementsForCreatedby(Set movementsForCreatedby) {
		this.movementsForCreatedby = movementsForCreatedby;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUpdatedby")
	public Set getStoragebinsForUpdatedby() {
		return this.storagebinsForUpdatedby;
	}

	public void setStoragebinsForUpdatedby(Set storagebinsForUpdatedby) {
		this.storagebinsForUpdatedby = storagebinsForUpdatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUpdatedby")
	public Set getProductOfferlinesForUpdatedby() {
		return this.productOfferlinesForUpdatedby;
	}

	public void setProductOfferlinesForUpdatedby(
			Set productOfferlinesForUpdatedby) {
		this.productOfferlinesForUpdatedby = productOfferlinesForUpdatedby;
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
	public Set getMovementlinesForUpdatedby() {
		return this.movementlinesForUpdatedby;
	}

	public void setMovementlinesForUpdatedby(Set movementlinesForUpdatedby) {
		this.movementlinesForUpdatedby = movementlinesForUpdatedby;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedby")
	public Set getProductOfferlinesForCreatedby() {
		return this.productOfferlinesForCreatedby;
	}

	public void setProductOfferlinesForCreatedby(
			Set productOfferlinesForCreatedby) {
		this.productOfferlinesForCreatedby = productOfferlinesForCreatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUpdatedby")
	public Set getOrderNumbersForUpdatedby() {
		return this.orderNumbersForUpdatedby;
	}

	public void setOrderNumbersForUpdatedby(Set orderNumbersForUpdatedby) {
		this.orderNumbersForUpdatedby = orderNumbersForUpdatedby;
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
