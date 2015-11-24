package com.mitosis.shopsbacker.vo.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mitosis.shopsbacker.model.Address;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.SalesOrderLine;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.vo.admin.StoreVo;
import com.mitosis.shopsbacker.vo.common.AddressVo;
import com.mitosis.shopsbacker.vo.customer.CustomerVo;

public class SalesOrderVo {
	private String salesOrderId;
	private CustomerVo customerVo;
	private StoreVo storeVo;
	private String orderNo;
	private String deliveryDate;
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
	private List<SalesOrderLineVo> salesOrderLineVo;
	private List<StoreVo> storeList = new ArrayList<StoreVo>();
	private AddressVo addressVo;
	private String fromDate;

	
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
	public String getDeliveryTimeSlotId() {
		return deliveryTimeSlotId;
	}
	public void setDeliveryTimeSlotId(String deliveryTimeSlotId) {
		this.deliveryTimeSlotId = deliveryTimeSlotId;
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
	public String getCustomerSign() {
		return customerSign;
	}
	public void setCustomerSign(String customerSign) {
		this.customerSign = customerSign;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public Date getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
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
	public CustomerVo getCustomerVo() {
		return customerVo;
	}
	public void setCustomerVo(CustomerVo customerVo) {
		this.customerVo = customerVo;
	}
	public StoreVo getStoreVo() {
		return storeVo;
	}
	public void setStoreVo(StoreVo storeVo) {
		this.storeVo = storeVo;
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
	
}
