package com.mitosis.shopsbacker.vo.customer;

// Generated Nov 12, 2015 6:16:19 PM 

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * SalesOrderLine Created by Sundaram C.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class SalesOrderLineVo implements java.io.Serializable {

	private String salesOrderLineId;
	private SalesOrderVo salesOrder;
	private ProductVo product;
	public String getSalesOrderLineId() {
		return salesOrderLineId;
	}
	public void setSalesOrderLineId(String salesOrderLineId) {
		this.salesOrderLineId = salesOrderLineId;
	}
	public SalesOrderVo getSalesOrder() {
		return salesOrder;
	}
	public void setSalesOrder(SalesOrderVo salesOrder) {
		this.salesOrder = salesOrder;
	}
	public ProductVo getProduct() {
		return product;
	}
	public void setProduct(ProductVo product) {
		this.product = product;
	}
	public String getSalesOrderId() {
		return salesOrderId;
	}
	public void setSalesOrderId(String salesOrderId) {
		this.salesOrderId = salesOrderId;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getGrossAmount() {
		return grossAmount;
	}
	public void setGrossAmount(BigDecimal grossAmount) {
		this.grossAmount = grossAmount;
	}
	public BigDecimal getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
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
	private String salesOrderId;
	private int qty;
	private BigDecimal price;
	private BigDecimal grossAmount;
	private BigDecimal netAmount;
	private BigDecimal discount;
	private char isactive;
	private Date created;
	private String createdby;
	private Date updated;
	private String updatedby;

	
}
