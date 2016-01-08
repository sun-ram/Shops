/**
 * 
 */
package com.mitosis.shopsbacker.model;

import java.math.BigDecimal;
import java.util.Date;

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
 * @author Anbukkani Gajendiran
 *
 */

@Entity
@Table(name = "billing")
public class Billing implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String billingId;
	private BigDecimal amount;
	private BigDecimal fees;
	private char isPaid;
	private char isactive;
	private Date orderedDate;
	private Date paidDate;
	private Store store;
	private Merchant merchant;
	private SalesOrder salesOrder;

	public Billing() {
	}

	public Billing(String billingId, BigDecimal amount, BigDecimal fees,
			char isPaid, char isactive, Date orderedDate, Date paidDate,
			Store store, Merchant merchant, SalesOrder salesOrder) {
		this.billingId = billingId;
		this.amount = amount;
		this.fees = fees;
		this.isPaid = isPaid;
		this.isactive = isactive;
		this.orderedDate = orderedDate;
		this.paidDate = paidDate;
		this.store = store;
		this.merchant = merchant;
		this.salesOrder = salesOrder;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "BILLING_ID", unique = true, nullable = false, length = 32)
	public String getBillingId() {
		return billingId;
	}

	public void setBillingId(String billingId) {
		this.billingId = billingId;
	}

	@Column(name = "AMOUNT", nullable = false, precision = 15)
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Column(name = "FEES", nullable = false, precision = 15)
	public BigDecimal getFees() {
		return fees;
	}

	public void setFees(BigDecimal fees) {
		this.fees = fees;
	}

	@Column(name = "IS_PAID", nullable = false, length = 1)
	public char getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(char isPaid) {
		this.isPaid = isPaid;
	}

	@Column(name = "ISACTIVE", nullable = false, length = 1)
	public char getIsactive() {
		return isactive;
	}

	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ORDERED_DATE", nullable = false, length = 19)
	public Date getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(Date orderedDate) {
		this.orderedDate = orderedDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PAID_DATE", nullable = true, length = 19)
	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
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
	@JoinColumn(name = "MERCHANT_ID")
	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	@ManyToOne
	@JoinColumn(name = "SALES_ORDER_ID")
	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}

}
