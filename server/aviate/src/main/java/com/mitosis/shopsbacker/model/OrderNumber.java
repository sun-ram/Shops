package com.mitosis.shopsbacker.model;

// Generated Nov 12, 2015 6:16:19 PM 

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

/**
 * OrderNumber Created by Sundaram C.
 */
@Entity
@Table(name = "order_number", uniqueConstraints = @UniqueConstraint(columnNames = {
		"STORE_ID", "MERCHANT_ID" }))
public class OrderNumber implements java.io.Serializable {

	private String orderNumberId;
	private String updatedby;
	private String createdby;
	private Merchant merchant;
	private Store store;
	private int startingNumber;
	private String prefix;
	private String suffix;
	private Integer nextNumber;
	private char isactive;
	private Date created;
	private Date updated;

	public OrderNumber() {
	}

	public OrderNumber(String orderNumberId, String updatedby,
			String createdby, Merchant merchant, Store store,
			int startingNumber, char isactive, Date created, Date updated) {
		this.orderNumberId = orderNumberId;
		this.updatedby = updatedby;
		this.createdby = createdby;
		this.merchant = merchant;
		this.store = store;
		this.startingNumber = startingNumber;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	public OrderNumber(String orderNumberId, String updatedby,
			String createdby, Merchant merchant, Store store,
			int startingNumber, String prefix, String suffix,
			Integer nextNumber, char isactive, Date created, Date updated) {
		this.orderNumberId = orderNumberId;
		this.updatedby = updatedby;
		this.createdby = createdby;
		this.merchant = merchant;
		this.store = store;
		this.startingNumber = startingNumber;
		this.prefix = prefix;
		this.suffix = suffix;
		this.nextNumber = nextNumber;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ORDER_NUMBER_ID", unique = true, nullable = false, length = 32)
	public String getOrderNumberId() {
		return this.orderNumberId;
	}

	public void setOrderNumberId(String orderNumberId) {
		this.orderNumberId = orderNumberId;
	}

	public String getUpdatedby() {
		return this.updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public String getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
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
	@JoinColumn(name = "STORE_ID", nullable = false)
	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	@Column(name = "STARTING_NUMBER", nullable = false)
	public int getStartingNumber() {
		return this.startingNumber;
	}

	public void setStartingNumber(int startingNumber) {
		this.startingNumber = startingNumber;
	}

	@Column(name = "PREFIX", length = 10)
	public String getPrefix() {
		return this.prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Column(name = "SUFFIX", length = 10)
	public String getSuffix() {
		return this.suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	@Column(name = "NEXT_NUMBER")
	public Integer getNextNumber() {
		return this.nextNumber;
	}

	public void setNextNumber(Integer nextNumber) {
		this.nextNumber = nextNumber;
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

}
