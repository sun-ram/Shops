package com.mitosis.shopsbacker.vo.order;

import java.math.BigDecimal;

import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.SalesOrderLine;
import com.mitosis.shopsbacker.model.SalesOrderReturn;

public class SalesOrderReturnLineVo {
	
	private String salesOrderReturnLineId;
	private SalesOrderReturn salesOrderReturn;
	private SalesOrderLine salesOrderLine;
	private Product product;
	private int quantity;
	private BigDecimal price;
	private BigDecimal returnGrossAmount;
	private BigDecimal returnNetAmount;
	
	public String getSalesOrderReturnLineId() {
		return salesOrderReturnLineId;
	}
	public void setSalesOrderReturnLineId(String salesOrderReturnLineId) {
		this.salesOrderReturnLineId = salesOrderReturnLineId;
	}
	public SalesOrderReturn getSalesOrderReturn() {
		return salesOrderReturn;
	}
	public void setSalesOrderReturn(SalesOrderReturn salesOrderReturn) {
		this.salesOrderReturn = salesOrderReturn;
	}
	public SalesOrderLine getSalesOrderLine() {
		return salesOrderLine;
	}
	public void setSalesOrderLine(SalesOrderLine salesOrderLine) {
		this.salesOrderLine = salesOrderLine;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getReturnGrossAmount() {
		return returnGrossAmount;
	}
	public void setReturnGrossAmount(BigDecimal returnGrossAmount) {
		this.returnGrossAmount = returnGrossAmount;
	}
	public BigDecimal getReturnNetAmount() {
		return returnNetAmount;
	}
	public void setReturnNetAmount(BigDecimal returnNetAmount) {
		this.returnNetAmount = returnNetAmount;
	}
	
}
