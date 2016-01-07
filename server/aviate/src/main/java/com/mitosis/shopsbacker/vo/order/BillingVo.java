package com.mitosis.shopsbacker.vo.order;

import java.math.BigDecimal;

import com.mitosis.shopsbacker.vo.admin.MerchantVo;
import com.mitosis.shopsbacker.vo.admin.StoreVo;

/**
 * @author Kathir
 *
 */
public class BillingVo {
	
	private String billingId;
	private BigDecimal amount;
	private BigDecimal fees;
	private char isPaid;
	private String orderedDate;
	private String paidDate;
	private StoreVo store;
	private MerchantVo merchant;
	private SalesOrderVo salesOrder;
	
	public String getBillingId() {
		return billingId;
	}
	public void setBillingId(String billingId) {
		this.billingId = billingId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getFees() {
		return fees;
	}
	public void setFees(BigDecimal fees) {
		this.fees = fees;
	}
	public char getIsPaid() {
		return isPaid;
	}
	public void setIsPaid(char isPaid) {
		this.isPaid = isPaid;
	}
	
	public String getOrderedDate() {
		return orderedDate;
	}
	public void setOrderedDate(String orderedDate) {
		this.orderedDate = orderedDate;
	}
	public String getPaidDate() {
		return paidDate;
	}
	public void setPaidDate(String paidDate) {
		this.paidDate = paidDate;
	}
	
	public StoreVo getStore() {
		return store;
	}
	public void setStore(StoreVo store) {
		this.store = store;
	}
	public MerchantVo getMerchant() {
		return merchant;
	}
	public void setMerchant(MerchantVo merchant) {
		this.merchant = merchant;
	}
	public SalesOrderVo getSalesOrder() {
		return salesOrder;
	}
	public void setSalesOrder(SalesOrderVo salesOrder) {
		this.salesOrder = salesOrder;
	}
}
