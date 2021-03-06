package com.mitosis.shopsbacker.model;

// Generated Nov 12, 2015 6:16:19 PM 

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * SalesOrderLine Created by Sundaram C.
 */
@Entity
@Table(name = "sales_order_line")
public class SalesOrderLine implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String salesOrderLineId;
	private SalesOrder salesOrder;
	private Product product;
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
	private List<SalesOrderReturnLine> salesOrderReturnLines = new ArrayList<SalesOrderReturnLine>();

	public SalesOrderLine() {
	}

	public SalesOrderLine(SalesOrder salesOrder, Product product,
			int qty, BigDecimal price,
			BigDecimal grossAmount, BigDecimal netAmount, BigDecimal discount,
			char isactive, Date created, Date updated) {
		this.salesOrder = salesOrder;
		this.product = product;
		this.qty = qty;
		this.price = price;
		this.grossAmount = grossAmount;
		this.netAmount = netAmount;
		this.discount = discount;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	public SalesOrderLine(SalesOrder salesOrder, Product product,
			int qty, BigDecimal price,
			BigDecimal grossAmount, BigDecimal netAmount, BigDecimal discount,
			char isactive, Date created, String createdby, Date updated,
			String updatedby, List<SalesOrderReturnLine> salesOrderReturnLines) {
		this.salesOrder = salesOrder;
		this.product = product;
		this.qty = qty;
		this.price = price;
		this.grossAmount = grossAmount;
		this.netAmount = netAmount;
		this.discount = discount;
		this.isactive = isactive;
		this.created = created;
		this.createdby = createdby;
		this.updated = updated;
		this.updatedby = updatedby;
		this.salesOrderReturnLines = salesOrderReturnLines;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "SALES_ORDER_LINE_ID", unique = true, nullable = false, length = 32)
	public String getSalesOrderLineId() {
		return this.salesOrderLineId;
	}

	public void setSalesOrderLineId(String salesOrderLineId) {
		this.salesOrderLineId = salesOrderLineId;
	}

	@ManyToOne
	@JoinColumn(name = "SALES_ORDER_ID")
	public SalesOrder getSalesOrder() {
		return this.salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "QTY", nullable = false)
	public int getQty() {
		return this.qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	@Column(name = "PRICE", nullable = false, precision = 15)
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name = "GROSS_AMOUNT", nullable = false, precision = 15)
	public BigDecimal getGrossAmount() {
		return this.grossAmount;
	}

	public void setGrossAmount(BigDecimal grossAmount) {
		this.grossAmount = grossAmount;
	}

	@Column(name = "NET_AMOUNT", nullable = false, precision = 15)
	public BigDecimal getNetAmount() {
		return this.netAmount;
	}

	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
	}

	@Column(name = "DISCOUNT", nullable = false, precision = 15)
	public BigDecimal getDiscount() {
		return this.discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	@Column(name = "ISACTIVE", nullable = false, length = 1)
	public char getIsactive() {
		return this.isactive;
	}

	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED", nullable = false, length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name = "CREATEDBY", length = 32)
	public String getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED", nullable = false, length = 19)
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Column(name = "UPDATEDBY", length = 32)
	public String getUpdatedby() {
		return this.updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "salesOrderLine")
	public List<SalesOrderReturnLine> getSalesOrderReturnLines() {
		return salesOrderReturnLines;
	}

	public void setSalesOrderReturnLines(
			List<SalesOrderReturnLine> salesOrderReturnLines) {
		this.salesOrderReturnLines = salesOrderReturnLines;
	}

}
