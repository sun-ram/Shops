package com.mitosis.shopsbacker.vo.customer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class SalesOrderVo {
	private Long salesOrderId;
	private String orderId;
	private Long customerId;
	private Long addressId;
	private Long storeId;
	private boolean active;
	private String isPaid;
	private Long orderNo;
	private Long contactNo;
	private Double totalAmount;
	private Date deliveryDate;
	private String deliveryTime;
	private String paymentMethod;
	private String transactionNo;
	private String orderStatus;
	private byte[] userSign;
	private Double totalTaxAmount;
	private Double orderGrossAmount;
	private Double shippingCharge;
	private Long merchantId;
	private Date created;
	private Long warehouseId;
	
	//private List<SalesOrderLineModel> product = new ArrayList<SalesOrderLineModel>();
	
	//private Address address;
	
	//private StoreModel store;
	
	//private CustomerModel customer;
	
	//private WarehouseModel warehouse;
	
	/*public CustomerModel getCustomer() {
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
	}*/

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

	/*public List<SalesOrderLineModel> getProduct() {
		return product;
	}

	public void setProduct(List<SalesOrderLineModel> product) {
		this.product = product;
	}*/

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

	/*public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}*/
	
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

	/*public WarehouseModel getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(WarehouseModel warehouse) {
		this.warehouse = warehouse;
	}*/

	
	
	
}
