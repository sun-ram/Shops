package com.mitosis.shopsbacker.vo.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.SalesOrderReturnLine;
import com.mitosis.shopsbacker.model.Store;

public class SalesOrderReturnVo {
	
	private String salesOrderReturnId;
	private SalesOrderVo salesOrder;
	private String returnReason;
	private BigDecimal returnTotalAmount;
	private String returnStatus;
	private BigDecimal returnTaxAmount;
	private Store store;
	private Merchant merchant;
	private BigDecimal shippingCharge;
	private char ispaid;
	private List<SalesOrderReturnLine> salesOrderReturnLines = new ArrayList<SalesOrderReturnLine>();
	public String getSalesOrderReturnId() {
		return salesOrderReturnId;
	}
	public void setSalesOrderReturnId(String salesOrderReturnId) {
		this.salesOrderReturnId = salesOrderReturnId;
	}
	
	public SalesOrderVo getSalesOrder() {
		return salesOrder;
	}
	public void setSalesOrder(SalesOrderVo salesOrder) {
		this.salesOrder = salesOrder;
	}
	public String getReturnReason() {
		return returnReason;
	}
	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}
	public BigDecimal getReturnTotalAmount() {
		return returnTotalAmount;
	}
	public void setReturnTotalAmount(BigDecimal returnTotalAmount) {
		this.returnTotalAmount = returnTotalAmount;
	}
	public String getReturnStatus() {
		return returnStatus;
	}
	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}
	public BigDecimal getReturnTaxAmount() {
		return returnTaxAmount;
	}
	public void setReturnTaxAmount(BigDecimal returnTaxAmount) {
		this.returnTaxAmount = returnTaxAmount;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public Merchant getMerchant() {
		return merchant;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	public BigDecimal getShippingCharge() {
		return shippingCharge;
	}
	public void setShippingCharge(BigDecimal shippingCharge) {
		this.shippingCharge = shippingCharge;
	}
	
	public char getIspaid() {
		return ispaid;
	}
	public void setIspaid(char ispaid) {
		this.ispaid = ispaid;
	}
	public List<SalesOrderReturnLine> getSalesOrderReturnLines() {
		return salesOrderReturnLines;
	}
	public void setSalesOrderReturnLines(
			List<SalesOrderReturnLine> salesOrderReturnLines) {
		this.salesOrderReturnLines = salesOrderReturnLines;
	}
	
}
