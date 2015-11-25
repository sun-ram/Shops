package com.mitosis.shopsbacker.model;	
// Generated Nov 23, 2015 5:56:32 PM 

import java.math.BigDecimal;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * SalesOrder generated 
 */
@Entity
@Table(name = "sales_order", catalog = "shopsbacker")
public class SalesOrder implements java.io.Serializable {

	private String salesOrderId;
	private User backer;
	private Customer customer;
	private CustomerFeedback customerFeedback;
	private Address address;
	private User shopper;
	private Store store;
	private String orderNo;
	private Date deliveryDate;
	private String deliveryTimeSlot;
	private char ispaid;
	private BigDecimal amount;
	private String paymentMethod;
	private String transactionNo;
	private String status;
	private BigDecimal totalTaxAmount;
	private BigDecimal shippingCharge;
	private BigDecimal netAmount;
	private String customerSign;
	private Merchant merchant;
	private Date deliveryTime;
	private char deliveryFlag;
	private BigDecimal discountAmount;
	private char isactive;
	private String createdby;
	private Date created;
	private Date updated;
	private String updatedby;
	private List<OrderTax> orderTaxes = new ArrayList<OrderTax>();
	private List<SalesOrderLine> salesOrderLines = new ArrayList<SalesOrderLine>();
	private List<Favourite> favourites = new ArrayList<Favourite>();

	public SalesOrder() {
	}

	public SalesOrder(String salesOrderId, Customer customer, Address address,
			Store store, String orderNo, Date deliveryDate,
			String deliveryTimeSlot, char ispaid, BigDecimal amount,
			BigDecimal totalTaxAmount, BigDecimal shippingCharge,
			BigDecimal netAmount, Merchant merchant, BigDecimal discountAmount,
			char isactive, Date created, Date updated) {
		this.salesOrderId = salesOrderId;
		this.customer = customer;
		this.address = address;
		this.store = store;
		this.orderNo = orderNo;
		this.deliveryDate = deliveryDate;
		this.deliveryTimeSlot = deliveryTimeSlot;
		this.ispaid = ispaid;
		this.amount = amount;
		this.totalTaxAmount = totalTaxAmount;
		this.shippingCharge = shippingCharge;
		this.netAmount = netAmount;
		this.merchant = merchant;
		this.discountAmount = discountAmount;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	public SalesOrder(String salesOrderId, User backer,
			Customer customer, CustomerFeedback customerFeedback,
			Address address, User shopper, Store store, String orderNo,
			Date deliveryDate, String deliveryTimeSlot, char ispaid,
			BigDecimal amount, String paymentMethod, String transactionNo,
			String status, BigDecimal totalTaxAmount,
			BigDecimal shippingCharge, BigDecimal netAmount,
			String customerSign, Merchant merchant, Date deliveryTime,
			char deliveryFlag, BigDecimal discountAmount, char isactive,
			String createdby, Date created, Date updated, String updatedby) {
		this.salesOrderId = salesOrderId;
		this.backer = backer;
		this.customer = customer;
		this.customerFeedback = customerFeedback;
		this.address = address;
		this.shopper = shopper;
		this.store = store;
		this.orderNo = orderNo;
		this.deliveryDate = deliveryDate;
		this.deliveryTimeSlot = deliveryTimeSlot;
		this.ispaid = ispaid;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.transactionNo = transactionNo;
		this.status = status;
		this.totalTaxAmount = totalTaxAmount;
		this.shippingCharge = shippingCharge;
		this.netAmount = netAmount;
		this.customerSign = customerSign;
		this.merchant = merchant;
		this.deliveryTime = deliveryTime;
		this.deliveryFlag = deliveryFlag;
		this.discountAmount = discountAmount;
		this.isactive = isactive;
		this.createdby = createdby;
		this.created = created;
		this.updated = updated;
		this.updatedby = updatedby;
		
	}

	@Id
	@GeneratedValue(generator = "system-uuid")		
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "SALES_ORDER_ID", unique = true, nullable = false, length = 32)
	public String getSalesOrderId() {
		return this.salesOrderId;
	}

	public void setSalesOrderId(String salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

	@Column(name = "ORDER_NO", nullable = false, length = 45)
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DELIVERY_DATE", nullable = false, length = 19)
	public Date getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@Column(name = "DELIVERY_TIME_SLOT", nullable = false, length = 32)
	public String getDeliveryTimeSlot() {
		return this.deliveryTimeSlot;
	}

	public void setDeliveryTimeSlot(String deliveryTimeSlot) {
		this.deliveryTimeSlot = deliveryTimeSlot;
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

	@ManyToOne
	@JoinColumn(name = "MERCHANT_ID", nullable = false)
	public Merchant getMerchant() {
		return this.merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
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
	public char getDeliveryFlag() {
		return this.deliveryFlag;
	}

	public void setDeliveryFlag(char deliveryFlag) {
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
	
	@ManyToOne
	@JoinColumn(name = "BACKER_ID")
	public User getBacker() {
		return backer;
	}

	public void setBacker(User backer) {
		this.backer = backer;
	}
	
	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID", nullable = false)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@ManyToOne
	@JoinColumn(name = "CUSTOMER_FEEDBACK_ID")
	public CustomerFeedback getCustomerFeedback() {
		return customerFeedback;
	}

	public void setCustomerFeedback(CustomerFeedback customerFeedback) {
		this.customerFeedback = customerFeedback;
	}
	
	@ManyToOne
	@JoinColumn(name = "ADDRESS_ID", nullable = false)
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	@ManyToOne
	@JoinColumn(name = "SHOPPER_ID")
	public User getShopper() {
		return shopper;
	}

	public void setShopper(User shopper) {
		this.shopper = shopper;
	}
	
	
	@ManyToOne
	@JoinColumn(name = "STORE_ID")
	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
	
	@OneToMany(mappedBy = "salesOrder")
	public List<OrderTax> getOrderTaxes() {
		return this.orderTaxes;
	}

	public void setOrderTaxes(List<OrderTax> orderTaxes) {
		this.orderTaxes = orderTaxes;
	}

	@OneToMany(mappedBy = "salesOrder")
	public List<SalesOrderLine> getSalesOrderLines() {
		return this.salesOrderLines;
	}

	public void setSalesOrderLines(List<SalesOrderLine> salesOrderLines) {
		this.salesOrderLines = salesOrderLines;
	}

	@OneToMany(mappedBy = "salesOrder")
	public List<Favourite> getFavourites() {
		return this.favourites;
	}

	public void setFavourites(List<Favourite> favourites) {
		this.favourites = favourites;
	}
	
}