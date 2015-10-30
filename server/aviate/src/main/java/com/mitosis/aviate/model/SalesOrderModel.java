package com.mitosis.aviate.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@Entity
@Table(name = "sales_order")
public class SalesOrderModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sales_order_id")
	private Long salesOrderId;
	@Column(name="order_id")
	private String orderId;
	@Column(name="customer_id")
	private Long customerId;
	@Column(name="address_id")
	private Long addressId;
	@Column(name="store_id")
	private Long storeId;
	@Column(name="active")
	private boolean active;
	@Column(name="ispaid")
	private String isPaid;
	@Column(name="order_no")
	private Long orderNo;
	@Column(name="contact_number")
	private Long contactNo;
	@Column(name="order_amount")
	private Double totalAmount;
	@Column(name="delivery_date")
	@Temporal(TemporalType.DATE)
	private Date deliveryDate;
	@Column(name="delivery_time")
	private String deliveryTime;
	@Column(name="payment_method")
	private String paymentMethod;
	@Column(name="transaction_no")
	private String transactionNo;
	@Column(name="order_status")
	private String orderStatus;
	@Column(name="customer_sign")
	private byte[] userSign;
	@Column(name="total_tax_amount")
	private Double totalTaxAmount;
	@Column(name="order_gross_amount")
	private Double orderGrossAmount;
	@Column(name="shipping_charge")
	private Double shippingCharge;
	@Column(name="merchant_id")
	private Long merchantId;
	@Column(name="created")
	@Temporal(TemporalType.DATE)
	private Date created;
	@Column(name="warehouse_id")
	private Long warehouseId;
	
	@OneToMany(orphanRemoval=true, cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="sales_order_id")
	private List<SalesOrderLineModel> product = new ArrayList<SalesOrderLineModel>();
	
	@OneToOne (cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="address_id", unique= true, nullable=true, insertable=false, updatable=false, referencedColumnName="address_id")
	private Address address;
	
	@OneToOne (cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="store_id", unique= true, nullable=true, insertable=false, updatable=false, referencedColumnName="store_id")
	private StoreModel store;
	
	@OneToOne (cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="customer_id", unique= true, nullable=true, insertable=false, updatable=false, referencedColumnName="customer_id")
	private CustomerModel customer;
	
	@OneToOne (cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="warehouse_id", unique= true, nullable=true, insertable=false, updatable=false, referencedColumnName="warehouse_id")
	private WarehouseModel warehouse;
	
	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	public StoreModel getStore() {
		return store;
	}

	public void setStore(StoreModel store) {
		this.store = store;
	}

	public Long getSalesOrderId() {
		return salesOrderId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public Long getAddressId() {
		return addressId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public boolean isActive() {
		return active;
	}

	public void setSalesOrderId(Long salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public List<SalesOrderLineModel> getProduct() {
		return product;
	}

	public void setProduct(List<SalesOrderLineModel> product) {
		this.product = product;
	}

	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	public Long getContactNo() {
		return contactNo;
	}

	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(String isPaid) {
		this.isPaid = isPaid;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	@XmlTransient
	public byte[] getUserSign() {
		return userSign;
	}

	public void setUserSign(byte[] userSign) {
		this.userSign = userSign;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public String getTransactionNo() {
		return transactionNo;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	public Double getTotalTaxAmount() {
		return totalTaxAmount;
	}

	public void setTotalTaxAmount(Double totalTaxAmount) {
		this.totalTaxAmount = totalTaxAmount;
	}

	public Double getOrderGrossAmount() {
		return orderGrossAmount;
	}

	public void setOrderGrossAmount(Double orderGrossAmount) {
		this.orderGrossAmount = orderGrossAmount;
	}

	public Double getShippingCharge() {
		return shippingCharge;
	}

	public void setShippingCharge(Double shippingCharge) {
		this.shippingCharge = shippingCharge;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getDeliveryDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String deliveryDate = sdf.format(this.deliveryDate); 
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) throws ParseException {
		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		this.deliveryDate  = sdf.parse(deliveryDate);
	}

	public String getCreated() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String created = sdf.format(this.created); 
		return created;
	}
	public void setCreated(Date created){
		this.created = created;
	}

	public String getDeliveryTime() {
		
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	public WarehouseModel getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(WarehouseModel warehouse) {
		this.warehouse = warehouse;
	}

	
	
	
}
