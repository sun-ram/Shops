package com.mitosis.shopsbacker.vo.customer;

// Generated Nov 12, 2015 6:16:19 PM 

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.shopsbacker.vo.admin.MerchantVo;

/**
 * OrderTax Created by Sundaram C.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class OrderTaxVo implements java.io.Serializable {

	private String orderTaxId;
	private MerchantVo merchant;
	private TaxVo tax;
	private SalesOrderVo salesOrder;
	public String getOrderTaxId() {
		return orderTaxId;
	}
	public void setOrderTaxId(String orderTaxId) {
		this.orderTaxId = orderTaxId;
	}
	public MerchantVo getMerchant() {
		return merchant;
	}
	public void setMerchant(MerchantVo merchant) {
		this.merchant = merchant;
	}
	public TaxVo getTax() {
		return tax;
	}
	public void setTax(TaxVo tax) {
		this.tax = tax;
	}
	public SalesOrderVo getSalesOrder() {
		return salesOrder;
	}
	public void setSalesOrder(SalesOrderVo salesOrder) {
		this.salesOrder = salesOrder;
	}
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}
	public char getIsactive() {
		return isactive;
	}
	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
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
	private BigDecimal taxAmount;
	private char isactive;
	private Date created;
	private String createdby;
	private Date updated;
	private String updatedby;


}
