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
import javax.persistence.UniqueConstraint;

/**
 * ProductOffer Created by Sundaram C.
 */
@Entity
@Table(name = "product_offer", catalog = "shopsbacker", uniqueConstraints = @UniqueConstraint(columnNames = {
		"NAME", "MERCHANT_ID" }))
public class ProductOffer implements java.io.Serializable {

	private String productOfferId;
	private User userByCreatedby;
	private User userByUpdatedby;
	private Merchant merchant;
	private Product product;
	private String name;
	private String description;
	private Date fromDate;
	private Date todate;
	private Date startTime;
	private Date endTime;
	private char isactive;
	private Date created;
	private Date updated;
	private Set myCarts = new HashSet(0);

	public ProductOffer() {
	}

	public ProductOffer(String productOfferId, User userByCreatedby,
			User userByUpdatedby, Merchant merchant, Product product,
			String name, Date fromDate, Date todate, Date startTime,
			Date endTime, char isactive, Date created, Date updated) {
		this.productOfferId = productOfferId;
		this.userByCreatedby = userByCreatedby;
		this.userByUpdatedby = userByUpdatedby;
		this.merchant = merchant;
		this.product = product;
		this.name = name;
		this.fromDate = fromDate;
		this.todate = todate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	public ProductOffer(String productOfferId, User userByCreatedby,
			User userByUpdatedby, Merchant merchant, Product product,
			String name, String description, Date fromDate, Date todate,
			Date startTime, Date endTime, char isactive, Date created,
			Date updated, Set myCarts) {
		this.productOfferId = productOfferId;
		this.userByCreatedby = userByCreatedby;
		this.userByUpdatedby = userByUpdatedby;
		this.merchant = merchant;
		this.product = product;
		this.name = name;
		this.description = description;
		this.fromDate = fromDate;
		this.todate = todate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
		this.myCarts = myCarts;
	}

	@Id
	@Column(name = "PRODUCT_OFFER_ID", unique = true, nullable = false, length = 32)
	public String getProductOfferId() {
		return this.productOfferId;
	}

	public void setProductOfferId(String productOfferId) {
		this.productOfferId = productOfferId;
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
	@JoinColumn(name = "UPDATEDBY", nullable = false)
	public User getUserByUpdatedby() {
		return this.userByUpdatedby;
	}

	public void setUserByUpdatedby(User userByUpdatedby) {
		this.userByUpdatedby = userByUpdatedby;
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
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION", length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FROM_DATE", nullable = false, length = 19)
	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TODATE", nullable = false, length = 19)
	public Date getTodate() {
		return this.todate;
	}

	public void setTodate(Date todate) {
		this.todate = todate;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "START_TIME", nullable = false, length = 8)
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "END_TIME", nullable = false, length = 8)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productOffer")
	public Set getMyCarts() {
		return this.myCarts;
	}

	public void setMyCarts(Set myCarts) {
		this.myCarts = myCarts;
	}

}
