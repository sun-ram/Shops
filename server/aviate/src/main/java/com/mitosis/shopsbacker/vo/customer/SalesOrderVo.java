package com.mitosis.shopsbacker.vo.customer;

// Generated Nov 12, 2015 6:16:19 PM 

import java.math.BigDecimal;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.shopsbacker.vo.admin.AddressVo;

/**
 * SalesOrder Created by Sundaram C.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class SalesOrderVo implements java.io.Serializable {

	private String salesOrderId;
	private UserVo userByBackerId;
	private CustomerVo customer;
	private CustomerFeedbackVo customerFeedback;
	private AddressVo address;
	private UserVo userByShopperId;
	private StoreVo store;
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
	private List<OrderTaxVo> orderTaxes = new ArrayList<OrderTaxVo>();
	private SalesOrderLineVo salesOrderLine;
	private List<FavouriteVo> favourites = new ArrayList<FavouriteVo>();
	public String getSalesOrderId() {
		return salesOrderId;
	}
	public void setSalesOrderId(String salesOrderId) {
		this.salesOrderId = salesOrderId;
	}
	public UserVo getUserByBackerId() {
		return userByBackerId;
	}
	public void setUserByBackerId(UserVo userByBackerId) {
		this.userByBackerId = userByBackerId;
	}
	public CustomerVo getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerVo customer) {
		this.customer = customer;
	}
	public CustomerFeedbackVo getCustomerFeedback() {
		return customerFeedback;
	}
	public void setCustomerFeedback(CustomerFeedbackVo customerFeedback) {
		this.customerFeedback = customerFeedback;
	}
	public AddressVo getAddress() {
		return address;
	}
	public void setAddress(AddressVo address) {
		this.address = address;
	}
	public UserVo getUserByShopperId() {
		return userByShopperId;
	}
	public void setUserByShopperId(UserVo userByShopperId) {
		this.userByShopperId = userByShopperId;
	}
	public StoreVo getStore() {
		return store;
	}
	public void setStore(StoreVo store) {
		this.store = store;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
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
	public char getIsactive() {
		return isactive;
	}
	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public String getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
	public List<OrderTaxVo> getOrderTaxes() {
		return orderTaxes;
	}
	public void setOrderTaxes(List<OrderTaxVo> orderTaxes) {
		this.orderTaxes = orderTaxes;
	}
	public SalesOrderLineVo getSalesOrderLine() {
		return salesOrderLine;
	}
	public void setSalesOrderLine(SalesOrderLineVo salesOrderLine) {
		this.salesOrderLine = salesOrderLine;
	}
	public List<FavouriteVo> getFavourites() {
		return favourites;
	}
	public void setFavourites(List<FavouriteVo> favourites) {
		this.favourites = favourites;
	}

}
