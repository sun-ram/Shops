package com.mitosis.shopsbacker.model;

// Generated Nov 12, 2015 6:16:19 PM 

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
 * CustomerFeedback Created by Sundaram C.
 */
@Entity
@Table(name = "customer_feedback", catalog = "shopsbacker")
public class CustomerFeedback implements java.io.Serializable {

	private String customerFeedbackId;
	private Merchant merchant;
	private String createdby;
	private Customer customer;
	private String updatedby;
	private Store store;
	private String comments;
	private char isactive;
	private Date created;
	private Date updated;
	private List<SalesOrder> salesOrders = new ArrayList<SalesOrder>();

	public CustomerFeedback() {
	}

	public CustomerFeedback(String customerFeedbackId, Merchant merchant,
			String createdby, Customer customer,
			String updatedby, Store store, String comments,
			char isactive, Date created, Date updated) {
		this.customerFeedbackId = customerFeedbackId;
		this.merchant = merchant;
		this.createdby = createdby;
		this.customer = customer;
		this.updatedby = updatedby;
		this.store = store;
		this.comments = comments;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	public CustomerFeedback(String customerFeedbackId, Merchant merchant,
			String createdby, Customer customer,
			String updatedby, Store store, String comments,
			char isactive, Date created, Date updated, List<SalesOrder> salesOrders) {
		this.customerFeedbackId = customerFeedbackId;
		this.merchant = merchant;
		this.createdby = createdby;
		this.customer = customer;
		this.updatedby = updatedby;
		this.store = store;
		this.comments = comments;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
		this.salesOrders = salesOrders;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
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
 
	public String getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID", nullable = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getUpdatedby() {
		return this.updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
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
	public List<SalesOrder> getSalesOrders() {
		return this.salesOrders;
	}

	public void setSalesOrders(List<SalesOrder> salesOrders) {
		this.salesOrders = salesOrders;
	}

}
