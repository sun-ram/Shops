package com.mitosis.shopsbacker.vo.order;

import java.math.BigDecimal;
import java.util.Date;

import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.SalesOrder;

public class SalesOrderLineVo {
	private String salesOrderLineId;
	private SalesOrderVo salesOrderVo;
	private String salesOrderId;
	private int qty;
	private BigDecimal price;
	private BigDecimal grossAmount;
	private BigDecimal netAmount;
	private BigDecimal discount;
	private char isactive;
	public String getSalesOrderLineId() {
		return salesOrderLineId;
	}
	public void setSalesOrderLineId(String salesOrderLineId) {
		this.salesOrderLineId = salesOrderLineId;
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
	public SalesOrderVo getSalesOrderVo() {
		return salesOrderVo;
	}
	public void setSalesOrderVo(SalesOrderVo salesOrderVo) {
		this.salesOrderVo = salesOrderVo;
	}
	
	
}
