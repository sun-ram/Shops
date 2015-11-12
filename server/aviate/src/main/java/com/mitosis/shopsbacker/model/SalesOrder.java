package com.mitosis.shopsbacker.model;

// Generated Nov 12, 2015 3:32:23 PM 

import java.math.BigDecimal;
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

/**
 * SalesOrder Created by Sundaram C.
 */
@Entity
@Table(name = "sales_order", catalog = "shopsbacker")
public class SalesOrder implements java.io.Serializable {

	private String salesOrderId;
	private User userByBackerId;
	private Customer customer;
	private CustomerFeedback customerFeedback;
	private Address address;
	private User userByShopperId;
	private Store store;
	private String orderNo;
	private Date deliveryDate;
	private String deliveryTimeSlotId;
	private char ispaid;
	private BigDecimal amount;
	private String paymentMethod;
	private String transactionNo;
	private String status;
	private BigDecimal totalTaxAmount;
	private BigDecimal shippingCharge;
	private BigDecimal netAmount;
	private String customerSign;
	private String merchantId;
	private Date deliveryTime;
	private Character deliveryFlag;
	private BigDecimal discountAmount;
	private char isactive;
	private String createdby;
	private Date created;
	private Date updated;
	private String updatedby;
	private Set orderTaxes = new HashSet(0);
	private Set favourites = new HashSet(0);

	public SalesOrder() {
	}

	public SalesOrder(String salesOrderId, Customer customer, Address address,
			Store store, String orderNo, Date deliveryDate,
			String deliveryTimeSlotId, char ispaid, BigDecimal amount,
			BigDecimal totalTaxAmount, BigDecimal shippingCharge,
			BigDecimal netAmount, String merchantId, BigDecimal discountAmount,
			char isactive, Date created, Date updated) {
		this.salesOrderId = salesOrderId;
		this.customer = customer;
		this.address = address;
		this.store = store;
		this.orderNo = orderNo;
		this.deliveryDate = deliveryDate;
		this.deliveryTimeSlotId = deliveryTimeSlotId;
		this.ispaid = ispaid;
		this.amount = amount;
		this.totalTaxAmount = totalTaxAmount;
		this.shippingCharge = shippingCharge;
		this.netAmount = netAmount;
		this.merchantId = merchantId;
		this.discountAmount = discountAmount;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	public SalesOrder(String salesOrderId, User userByBackerId,
			Customer customer, CustomerFeedback customerFeedback,
			Address address, User userByShopperId, Store store, String orderNo,
			Date deliveryDate, String deliveryTimeSlotId, char ispaid,
			BigDecimal amount, String paymentMethod, String transactionNo,
			String status, BigDecimal totalTaxAmount,
			BigDecimal shippingCharge, BigDecimal netAmount,
			String customerSign, String merchantId, Date deliveryTime,
			Character deliveryFlag, BigDecimal discountAmount, char isactive,
			String createdby, Date created, Date updated, String updatedby,
			Set orderTaxes, Set favourites) {
		this.salesOrderId = salesOrderId;
		this.userByBackerId = userByBackerId;
		this.customer = customer;
		this.customerFeedback = customerFeedback;
		this.address = address;
		this.userByShopperId = userByShopperId;
		this.store = store;
		this.orderNo = orderNo;
		this.deliveryDate = deliveryDate;
		this.deliveryTimeSlotId = deliveryTimeSlotId;
		this.ispaid = ispaid;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.transactionNo = transactionNo;
		this.status = status;
		this.totalTaxAmount = totalTaxAmount;
		this.shippingCharge = shippingCharge;
		this.netAmount = netAmount;
		this.customerSign = customerSign;
		this.merchantId = merchantId;
		this.deliveryTime = deliveryTime;
		this.deliveryFlag = deliveryFlag;
		this.discountAmount = discountAmount;
		this.isactive = isactive;
		this.createdby = createdby;
		this.created = created;
		this.updated = updated;
		this.updatedby = updatedby;
		this.orderTaxes = orderTaxes;
		this.favourites = favourites;
	}

	@Id
	@Column(name = "SALES_ORDER_ID", unique = true, nullable = false, length = 32)
	public String getSalesOrderId() {
		return this.salesOrderId;
	}

	public void setSalesOrderId(String salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BACKER_ID")
	public User getUserByBackerId() {
		return this.userByBackerId;
	}

	public void setUserByBackerId(User userByBackerId) {
		this.userByBackerId = userByBackerId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID", nullable = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_FEEDBACK_ID")
	public CustomerFeedback getCustomerFeedback() {
		return this.customerFeedback;
	}

	public void setCustomerFeedback(CustomerFeedback customerFeedback) {
		this.customerFeedback = customerFeedback;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ADDRESS_ID", nullable = false)
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SHOPPER_ID")
	public User getUserByShopperId() {
		return this.userByShopperId;
	}

	public void setUserByShopperId(User userByShopperId) {
		this.userByShopperId = userByShopperId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STORE_ID", nullable = false)
	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	@Column(name = "ORDER_NO", nullable = false, length = 45)
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DELIVERY _DATE", nullable = false, length = 19)
	public Date getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@Column(name = "DELIVERY _TIME_SLOT_ID", nullable = false, length = 32)
	public String getDeliveryTimeSlotId() {
		return this.deliveryTimeSlotId;
	}

	public void setDeliveryTimeSlotId(String deliveryTimeSlotId) {
		this.deliveryTimeSlotId = deliveryTimeSlotId;
	}

	@Column(name = "ISPAID", nullable = false, length = 1)
	public char getIspaid() {
		return this.ispaid;
	}

	public void setIspaid(char ispaid) {
		this.ispaid = ispaid;
	}

	@Column(name = "AMOUNT", nullable = false, precision = 15)
	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Column(name = "PAYMENT_METHOD", length = 45)
	public String getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Column(name = "TRANSACTION_NO", length = 45)
	public String getTransactionNo() {
		return this.transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	@Column(name = "STATUS", length = 45)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "TOTAL_TAX_AMOUNT", nullable = false, precision = 15)
	public BigDecimal getTotalTaxAmount() {
		return this.totalTaxAmount;
	}

	public void setTotalTaxAmount(BigDecimal totalTaxAmount) {
		this.totalTaxAmount = totalTaxAmount;
	}

	@Column(name = "SHIPPING_CHARGE", nullable = false, precision = 15)
	public BigDecimal getShippingCharge() {
		return this.shippingCharge;
	}

	public void setShippingCharge(BigDecimal shippingCharge) {
		this.shippingCharge = shippingCharge;
	}

	@Column(name = "NET_AMOUNT", nullable = false, precision = 15)
	public BigDecimal getNetAmount() {
		return this.netAmount;
	}

	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
	}

	@Column(name = "CUSTOMER_SIGN", length = 32)
	public String getCustomerSign() {
		return this.customerSign;
	}

	public void setCustomerSign(String customerSign) {
		this.customerSign = customerSign;
	}

	@Column(name = "MERCHANT_ID", nullable = false, length = 32)
	public String getMerchantId() {
		return this.merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DELIVERY_TIME", length = 19)
	public Date getDeliveryTime() {
		return this.deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	@Column(name = "DELIVERY_FLAG", length = 1)
	public Character getDeliveryFlag() {
		return this.deliveryFlag;
	}

	public void setDeliveryFlag(Character deliveryFlag) {
		this.deliveryFlag = deliveryFlag;
	}

	@Column(name = "DISCOUNT_AMOUNT", nullable = false, precision = 15)
	public BigDecimal getDiscountAmount() {
		return this.discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	@Column(name = "ISACTIVE", nullable = false, length = 1)
	public char getIsactive() {
		return this.isactive;
	}

	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}

	@Column(name = "CREATEDBY", length = 32)
	public String getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
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

	@Column(name = "UPDATEDBY", length = 32)
	public String getUpdatedby() {
		return this.updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "salesOrder")
	public Set getOrderTaxes() {
		return this.orderTaxes;
	}

	public void setOrderTaxes(Set orderTaxes) {
		this.orderTaxes = orderTaxes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "salesOrder")
	public Set getFavourites() {
		return this.favourites;
	}

	public void setFavourites(Set favourites) {
		this.favourites = favourites;
	}

}
