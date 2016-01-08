/**
 * 
 */
package com.mitosis.shopsbacker.model;

import java.io.Serializable;
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
@Table(name = "Transaction")
public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;
	private String transactionId;
	private Store store;
	private String referenceNo;
	private BigDecimal amount;
	private String transactionType;
	private String transactionNo;
	private String status;
	private Merchant merchant;
	private char isactive;
	private String createdby;
	private Date created;
	private Date updated;
	private String updatedby;
	private String responseCode;
	private String responseMessage;
	private String recordId;
	private String paymentId;

	public Transaction(String transactionId, Store store, String referenceNo,
			BigDecimal amount, String transactionType, String transactionNo,
			String status, Merchant merchant, char isactive, String createdby,
			Date created, Date updated, String updatedby, String responseCode,
			String responseMessage, String recordId, String paymentId) {
		this.transactionId = transactionId;
		this.store = store;
		this.referenceNo = referenceNo;
		this.amount = amount;
		this.transactionType = transactionType;
		this.transactionNo = transactionNo;
		this.status = status;
		this.merchant = merchant;
		this.isactive = isactive;
		this.createdby = createdby;
		this.created = created;
		this.updated = updated;
		this.updatedby = updatedby;
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.recordId = recordId;
		this.paymentId = paymentId;
	}

	public Transaction() {

	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "TRANSACTION_ID", unique = true, nullable = false, length = 32)
	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	@ManyToOne
	@JoinColumn(name = "STORE_ID")
	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	@Column(name = "REFERENCE_NO", length = 45)
	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	@Column(name = "AMOUNT", precision = 15)
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Column(name = "TRANSACTION_TYPE", nullable = false, length = 60)
	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	@Column(name = "TRANSACTION_NO", nullable = false, length = 60)
	public String getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	@Column(name = "STATUS", length = 45)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ManyToOne
	@JoinColumn(name = "MERCHANT_ID", nullable = false)
	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
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

	@Column(name = "RESPONSE_CODE", length = 45)
	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	@Column(name = "RESPONSE_MESSAGE", length = 200)
	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	@Column(name = "RECORD_ID", length = 200)
	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	@Column(name = "PAYMENT_ID", length = 32)
	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

}
