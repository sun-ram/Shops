package com.mitosis.shopsbacker.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
 * @author prabakaran
 *
 */
@Entity
@Table(name = "sales_order_return")
public class SalesOrderReturn implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String salesOrderReturnId;
	private SalesOrder salesOrder;
	private String returnReason;
	private BigDecimal returnTotalAmount;
	private String returnStatus;
	private BigDecimal returnTaxAmount;
	private Store store;
	private Merchant merchant;
	private BigDecimal shippingCharge;
	private char isactive;
	private String createdby;
	private Date created;
	private Date updated;
	private String updatedby;
	private char ispaid;
	private List<SalesOrderReturnLine> salesOrderReturnLines = new ArrayList<SalesOrderReturnLine>();

	public SalesOrderReturn() {
		
	}
	
	public SalesOrderReturn(String salesOrderReturnId,SalesOrder salesOrder,String returnReason,
			BigDecimal returnTotalAmount,String returnStatus,BigDecimal returnTaxAmount,Store store,
			Merchant merchant,BigDecimal shippingCharge,char isactive,String createdby,  Date created, 
			Date updated, String updatedby, char ispaid,List<SalesOrderReturnLine> salesOrderReturnLines) {
		this.salesOrderReturnId = salesOrderReturnId;
		this.salesOrder = salesOrder;
		this.returnReason = returnReason;
		this.returnTotalAmount = returnTotalAmount;
		this.returnStatus = returnStatus;
		this.returnTaxAmount = returnTaxAmount;
		this.store = store;
		this.merchant = merchant;
		this.shippingCharge = shippingCharge;
		this.isactive = isactive;
		this.ispaid=ispaid;
		this.createdby = createdby;
		this.created = created;
		this.updated = updated;
		this.updatedby = updatedby;
		this.salesOrderReturnLines = salesOrderReturnLines;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")		
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "SALES_ORDER_RETURN_ID", unique = true, nullable = false, length = 32)
	public String getSalesOrderReturnId() {
		return salesOrderReturnId;
	}

	public void setSalesOrderReturnId(String salesOrderReturnId) {
		this.salesOrderReturnId = salesOrderReturnId;
	}

	
	

	@Column(name = "RETURN_REASON", nullable = false, length = 45)
	public String getReturnReason() {
		return returnReason;
	}


	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}
	
	
	@ManyToOne
	@JoinColumn(name = "SALES_ORDER_ID", nullable = false)
	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}


	@Column(name = "RETURN_TOTAL_AMOUNT", nullable = false, precision = 15, scale = 2)
	public BigDecimal getReturnTotalAmount() {
		return returnTotalAmount;
	}

	public void setReturnTotalAmount(BigDecimal returnTotalAmount) {
		this.returnTotalAmount = returnTotalAmount;
	}

	@Column(name = "RETURN_STATUS", nullable = false, length = 32)
	public String getReturnStatus() {
		return returnStatus;
	}

	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}

	@Column(name = "RETURN_TAX_AMOUNT", nullable = false, precision = 15, scale = 2)
	public BigDecimal getReturnTaxAmount() {
		return returnTaxAmount;
	}

	public void setReturnTaxAmount(BigDecimal returnTaxAmount) {
		this.returnTaxAmount = returnTaxAmount;
	}

	@ManyToOne
	@JoinColumn(name = "STORE_ID")
	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	@ManyToOne
	@JoinColumn(name = "MERCHANT_ID", nullable = false)
	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	
	@Column(name = "SHIPPING_CHARGE", precision = 15, scale = 2)
	public BigDecimal getShippingCharge() {
		return shippingCharge;
	}

	public void setShippingCharge(BigDecimal shippingCharge) {
		this.shippingCharge = shippingCharge;
	}

	@Column(name = "ISACTIVE", nullable = false, length = 1)
	public char getIsactive() {
		return isactive;
	}
	
	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}
	
	@Column(name = "ISPAID", nullable = false, length = 1)
	public char getIspaid() {
		return ispaid;
	}

	public void setIspaid(char ispaid) {
		this.ispaid = ispaid;
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
	
	@OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL,mappedBy = "salesOrderReturn")
	public List<SalesOrderReturnLine> getSalesOrderReturnLines() {
		return salesOrderReturnLines;
	}

	public void setSalesOrderReturnLines(
			List<SalesOrderReturnLine> salesOrderReturnLines) {
		this.salesOrderReturnLines = salesOrderReturnLines;
	}
	
}
