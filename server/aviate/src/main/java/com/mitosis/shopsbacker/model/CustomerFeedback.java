package com.mitosis.shopsbacker.model;

// Generated Nov 12, 2015 3:32:23 PM 

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CustomerFeedback Created by Sundaram C.
 */
@Entity
@Table(name = "customer_feedback", catalog = "shopsbacker")
public class CustomerFeedback implements java.io.Serializable {

	private String customerFeedbackId;
	private Merchant merchant;
	private Customer customerByCreatedby;
	private Customer customerByCustomerId;
	private Customer customerByUpdatedby;
	private Store store;
	private String comments;
	private char isactive;
	private Date created;
	private Date updated;
	private Set salesOrders = new HashSet(0);

	public CustomerFeedback() {
	}

	public CustomerFeedback(String customerFeedbackId, Merchant merchant,
			Customer customerByCreatedby, Customer customerByCustomerId,
			Customer customerByUpdatedby, Store store, String comments,
			char isactive, Date created, Date updated) {
		this.customerFeedbackId = customerFeedbackId;
		this.merchant = merchant;
		this.customerByCreatedby = customerByCreatedby;
		this.customerByCustomerId = customerByCustomerId;
		this.customerByUpdatedby = customerByUpdatedby;
		this.store = store;
		this.comments = comments;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	public CustomerFeedback(String customerFeedbackId, Merchant merchant,
			Customer customerByCreatedby, Customer customerByCustomerId,
			Customer customerByUpdatedby, Store store, String comments,
			char isactive, Date created, Date updated, Set salesOrders) {
		this.customerFeedbackId = customerFeedbackId;
		this.merchant = merchant;
		this.customerByCreatedby = customerByCreatedby;
		this.customerByCustomerId = customerByCustomerId;
		this.customerByUpdatedby = customerByUpdatedby;
		this.store = store;
		this.comments = comments;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
		this.salesOrders = salesOrders;
	}

	@Id
	@Column(name = "CUSTOMER_FEEDBACK_ID", unique = true, nullable = false, length = 32)
	public String getCustomerFeedbackId() {
		return this.customerFeedbackId;
	}

	public void setCustomerFeedbackId(String customerFeedbackId) {
		this.customerFeedbackId = customerFeedbackId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MERCHANT_ID", nullable = false)
	public Merchant getMerchant() {
		return this.merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATEDBY", nullable = false)
	public Customer getCustomerByCreatedby() {
		return this.customerByCreatedby;
	}

	public void setCustomerByCreatedby(Customer customerByCreatedby) {
		this.customerByCreatedby = customerByCreatedby;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID", nullable = false)
	public Customer getCustomerByCustomerId() {
		return this.customerByCustomerId;
	}

	public void setCustomerByCustomerId(Customer customerByCustomerId) {
		this.customerByCustomerId = customerByCustomerId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UPDATEDBY", nullable = false)
	public Customer getCustomerByUpdatedby() {
		return this.customerByUpdatedby;
	}

	public void setCustomerByUpdatedby(Customer customerByUpdatedby) {
		this.customerByUpdatedby = customerByUpdatedby;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STORE_ID", nullable = false)
	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	@Column(name = "COMMENTS", nullable = false, length = 500)
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED", nullable = false, length = 19)
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerFeedback")
	public Set getSalesOrders() {
		return this.salesOrders;
	}

	public void setSalesOrders(Set salesOrders) {
		this.salesOrders = salesOrders;
	}

}
