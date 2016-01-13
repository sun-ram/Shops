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
 * @author Kathir
 *
 */

@Entity
@Table(name = "billing_payment")
public class BillingPayment {
	
	private String billingPaymentId;
	private BigDecimal amount;
	private char isactive;
	private Merchant merchant;
	private String updatedby;
	private String createdby;
	private Date created;
	private Date updated;
	
	public BillingPayment() {
	}
	
	public BillingPayment(String billingPaymentId, BigDecimal amount,char isactive,
			Merchant merchant) {
		this.billingPaymentId = billingPaymentId;
		this.amount = amount;
		this.isactive = isactive;
		this.merchant = merchant;
	}
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "BILLING_PAYMENT_ID", unique = true, nullable = false, length = 32)
	public String getBillingPaymentId() {
		return billingPaymentId;
	}
	public void setBillingPaymentId(String billingPaymentId) {
		this.billingPaymentId = billingPaymentId;
	}
	
	@Column(name = "UPDATEDBY", length = 32)
	public String getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
	
	@Column(name = "CREATEDBY", length = 32)
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	
	@Column(name = "AMOUNT", nullable = false, precision = 15)
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	@Column(name = "ISACTIVE", nullable = false, precision = 15)
	public char getIsactive() {
		return isactive;
	}
	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}
	
	@ManyToOne
	@JoinColumn(name = "MERCHANT_ID")
	public Merchant getMerchant() {
		return merchant;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED", length = 19)
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED", length = 19)
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
}
