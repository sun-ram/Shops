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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Tax Created by Sundaram C.
 */
@Entity
@Table(name = "tax", catalog = "shopsbacker", uniqueConstraints = @UniqueConstraint(columnNames = {
		"MERCHANT_ID", "NAME" }))
public class Tax implements java.io.Serializable {

	private String taxId;
	private User userByUpdatedby;
	private User userByCreatedby;
	private Merchant merchant;
	private String name;
	private double taxPercentage;
	private char isactive;
	private Date created;
	private Date updated;
	private List<OrderTax> orderTaxes = new ArrayList<OrderTax>();

	public Tax() {
	}

	public Tax(String taxId, User userByUpdatedby, User userByCreatedby,
			Merchant merchant, String name, double taxPercentage,
			char isactive, Date created, Date updated) {
		this.taxId = taxId;
		this.userByUpdatedby = userByUpdatedby;
		this.userByCreatedby = userByCreatedby;
		this.merchant = merchant;
		this.name = name;
		this.taxPercentage = taxPercentage;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	public Tax(String taxId, User userByUpdatedby, User userByCreatedby,
			Merchant merchant, String name, double taxPercentage,
			char isactive, Date created, Date updated, List<OrderTax> orderTaxes) {
		this.taxId = taxId;
		this.userByUpdatedby = userByUpdatedby;
		this.userByCreatedby = userByCreatedby;
		this.merchant = merchant;
		this.name = name;
		this.taxPercentage = taxPercentage;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
		this.orderTaxes = orderTaxes;
	}

	@Id
	@Column(name = "TAX_ID", unique = true, nullable = false, length = 32)
	public String getTaxId() {
		return this.taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UPDATEDBY", nullable = false)
	public User getUserByUpdatedby() {
		return this.userByUpdatedby;
	}

	public void setUserByUpdatedby(User userByUpdatedby) {
		this.userByUpdatedby = userByUpdatedby;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATEDBY", nullable = false)
	public User getUserByCreatedby() {
		return this.userByCreatedby;
	}

	public void setUserByCreatedby(User userByCreatedby) {
		this.userByCreatedby = userByCreatedby;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MERCHANT_ID", nullable = false)
	public Merchant getMerchant() {
		return this.merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "TAX_PERCENTAGE", nullable = false, precision = 22, scale = 0)
	public double getTaxPercentage() {
		return this.taxPercentage;
	}

	public void setTaxPercentage(double taxPercentage) {
		this.taxPercentage = taxPercentage;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tax")
	public List<OrderTax> getOrderTaxes() {
		return this.orderTaxes;
	}

	public void setOrderTaxes(List<OrderTax> orderTaxes) {
		this.orderTaxes = orderTaxes;
	}

}
