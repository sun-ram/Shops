package com.mitosis.shopsbacker.vo.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mitosis.shopsbacker.vo.admin.MerchantVo;
import com.mitosis.shopsbacker.vo.admin.StoreVo;
import com.mitosis.shopsbacker.vo.admin.UserVo;
import com.mitosis.shopsbacker.vo.common.AddressVo;
import com.mitosis.shopsbacker.vo.common.ImageVo;
import com.mitosis.shopsbacker.vo.customer.CustomerVo;

public class SalesOrderVo {
	private String salesOrderId;
	private StoreVo store;
	private CustomerVo customer;
	private AddressVo address;
	private String orderNo;
	private String deliveryDate;
	private String deliveryTimeSlot;
	private char ispaid;
	private BigDecimal amount;
	private String paymentMethod;
	private String transactionNo;
	private String status;
	private BigDecimal totalTaxAmount;
	private BigDecimal shippingCharge;
	private BigDecimal netAmount;
	private ImageVo sign;
	private MerchantVo merchant;
	private Character deliveryFlag;
	private BigDecimal discountAmount;
	private List<SalesOrderLineVo> salesOrderLineVo;
	private List<StoreVo> storeList = new ArrayList<StoreVo>();
	private AddressVo addressVo;
	private String fromDate;
	private UserVo user;
	private UserVo shoper;
	private UserVo backer;
	private String shoperId;
	private String backerId;
	private String customerId;
	private String storeId;
	private String deliveredTime;
	private String pickupStartTime;
	private String packedTime;
	private String deliveryStartTime;
	private String shopperAssignedTime;
	private String backerAssignedTime;
	
	public ImageVo getSign() {
		return sign;
	}
	public void setSign(ImageVo sign) {
		this.sign = sign;
	}
	public String getBackerId() {
		return backerId;
	}
	public void setBackerId(String backerId) {
		this.backerId = backerId;
	}
	public String getShoperId() {
		return shoperId;
	}
	public void setShoperId(String shoperId) {
		this.shoperId = shoperId;
	}
	private List<OrderTaxVo> orderTax;
	
	public String getSalesOrderId() {
		return salesOrderId;
	}
	public void setSalesOrderId(String salesOrderId) {
		this.salesOrderId = salesOrderId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getDeliveryTimeSlot() {
		return deliveryTimeSlot;
	}
	public void setDeliveryTimeSlot(String deliveryTimeSlot) {
		this.deliveryTimeSlot = deliveryTimeSlot;
	}
	public char getIspaid() {
		return ispaid;
	}
	public void setIspaid(char ispaid) {
		this.ispaid = ispaid;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getTransactionNo() {
		return transactionNo;
	}
	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getTotalTaxAmount() {
		return totalTaxAmount;
	}
	public void setTotalTaxAmount(BigDecimal totalTaxAmount) {
		this.totalTaxAmount = totalTaxAmount;
	}
	public BigDecimal getShippingCharge() {
		return shippingCharge;
	}
	public void setShippingCharge(BigDecimal shippingCharge) {
		this.shippingCharge = shippingCharge;
	}
	public BigDecimal getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
	}
	public MerchantVo getMerchant() {
		return merchant;
	}
	public void setMerchant(MerchantVo merchant) {
		this.merchant = merchant;
	}
	public Character getDeliveryFlag() {
		return deliveryFlag;
	}
	public void setDeliveryFlag(Character deliveryFlag) {
		this.deliveryFlag = deliveryFlag;
	}
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}
	public StoreVo getStore() {
		return store;
	}
	public void setStore(StoreVo storeVo) {
		this.store = storeVo;
	}
	public List<SalesOrderLineVo> getSalesOrderLineVo() {
		return salesOrderLineVo;
	}
	public void setSalesOrderLineVo(List<SalesOrderLineVo> salesOrderLineVo) {
		this.salesOrderLineVo = salesOrderLineVo;
	}
	public AddressVo getAddressVo() {
		return addressVo;
	}
	public void setAddressVo(AddressVo addressVo) {
		this.addressVo = addressVo;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public List<StoreVo> getStoreList() {
		return storeList;
	}
	public void setStoreList(List<StoreVo> storeList) {
		this.storeList = storeList;
	}
	public CustomerVo getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerVo customer) {
		this.customer = customer;
	}
	public AddressVo getAddress() {
		return address;
	}
	public void setAddress(AddressVo address) {
		this.address = address;
	}
	public List<OrderTaxVo> getOrderTax() {
		return orderTax;
	}
	public void setOrderTax(List<OrderTaxVo> orderTax) {
		this.orderTax = orderTax;
	}
	public UserVo getUser() {
		return user;
	}
	public void setUser(UserVo user) {
		this.user = user;
	}
	public UserVo getShoper() {
		return shoper;
	}
	public void setShoper(UserVo shoper) {
		this.shoper = shoper;
	}
	public UserVo getBacker() {
		return backer;
	}
	public void setBacker(UserVo backer) {
		this.backer = backer;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getDeliveredTime() {
		return deliveredTime;
	}
	public void setDeliveredTime(String deliveredTime) {
		this.deliveredTime = deliveredTime;
	}
	public String getPickupStartTime() {
		return pickupStartTime;
	}
	public void setPickupStartTime(String pickupStartTime) {
		this.pickupStartTime = pickupStartTime;
	}
	public String getPackedTime() {
		return packedTime;
	}
	public void setPackedTime(String packedTime) {
		this.packedTime = packedTime;
	}
	public String getDeliveryStartTime() {
		return deliveryStartTime;
	}
	public void setDeliveryStartTime(String deliveryStartTime) {
		this.deliveryStartTime = deliveryStartTime;
	}
	public String getShopperAssignedTime() {
		return shopperAssignedTime;
	}
	public void setShopperAssignedTime(String shopperAssignedTime) {
		this.shopperAssignedTime = shopperAssignedTime;
	}
	public String getBackerAssignedTime() {
		return backerAssignedTime;
	}
	public void setBackerAssignedTime(String backerAssignedTime) {
		this.backerAssignedTime = backerAssignedTime;
	}
	
}
