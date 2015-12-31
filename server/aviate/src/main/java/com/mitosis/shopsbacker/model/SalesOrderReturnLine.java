package com.mitosis.shopsbacker.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;


/**
 * @author prabakaran
 *
 */
@Entity
@Table(name = "sales_order_return_line")
public class SalesOrderReturnLine implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String salesOrderReturnLineId;
	private SalesOrderReturn salesOrderReturn;
	private SalesOrderLine salesOrderLine;
	private Product product;
	private int quantity;
	private BigDecimal price;
	private BigDecimal returnGrossAmount;
	private BigDecimal returnNetAmount;
	private char isactive;
	private String createdby;
	private Date created;
	private Date updated;
	private String updatedby;
	
	public SalesOrderReturnLine() {
		
	}
	
	public SalesOrderReturnLine(String salesOrderReturnLineId, String salesOrderReturnId, Product product,
			int quantity, BigDecimal price, BigDecimal returnGrossAmount, BigDecimal returnNetAmount,
			char isactive,String createdby,  Date created, Date updated, String updatedby, SalesOrderReturn salesOrderReturn, SalesOrderLine salesOrderLine) {
		
		this.salesOrderReturnLineId = salesOrderReturnLineId;
		this.salesOrderReturn = salesOrderReturn;
		this.salesOrderLine = salesOrderLine;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
		this.returnGrossAmount = returnGrossAmount;
		this.returnNetAmount = returnNetAmount;
		this.isactive = isactive;
		this.createdby = createdby;
		this.created = created;
		this.updated = updated;
		this.updatedby = updatedby;
			
	}

	@Id
	@GeneratedValue(generator = "system-uuid")		
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "SALES_ORDER_RETURN_LINE_ID", unique = true, nullable = false, length = 32)
	public String getSalesOrderReturnLineId() {
		return salesOrderReturnLineId;
	}

	public void setSalesOrderReturnLineId(String salesOrderReturnLineId) {
		this.salesOrderReturnLineId = salesOrderReturnLineId;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SALES_ORDER_RETURN_ID")
	public SalesOrderReturn getSalesOrderReturn() {
		return salesOrderReturn;
	}

	public void setSalesOrderReturn(SalesOrderReturn salesOrderReturn) {
		this.salesOrderReturn = salesOrderReturn;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SALES_ORDER_LINE_ID")
	public SalesOrderLine getSalesOrderLine() {
		return salesOrderLine;
	}

	public void setSalesOrderLine(SalesOrderLine salesOrderLine) {
		this.salesOrderLine = salesOrderLine;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PRODUCT_ID")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "QTY", nullable = false, length = 11)
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name = "PRICE", nullable = false, precision = 15, scale = 2)
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name = "RETURN_GROSS_AMOUNT", nullable = false, precision = 15, scale = 2 )
	public BigDecimal getReturnGrossAmount() {
		return returnGrossAmount;
	}

	public void setReturnGrossAmount(BigDecimal returnGrossAmount) {
		this.returnGrossAmount = returnGrossAmount;
	}

	@Column(name = "RETURN_NET_AMOUNT", nullable = false, precision = 15, scale = 2)
	public BigDecimal getReturnNetAmount() {
		return returnNetAmount;
	}

	public void setReturnNetAmount(BigDecimal returnNetAmount) {
		this.returnNetAmount = returnNetAmount;
	}
	@Column(name = "ISACTIVE", nullable = false, length = 1)
	public char getIsactive() {
		return isactive;
	}

	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}

	@Column(name = "CREATEDBY", length = 32)
	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED", nullable = false, length = 19)
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED", nullable = false, length = 19)
	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Column(name = "UPDATEDBY", length = 32)
	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
}
