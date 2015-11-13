package com.mitosis.shopsbacker.model;

// Generated Nov 12, 2015 6:16:19 PM 

import java.math.BigDecimal;
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
 * Discount Created by Sundaram C.
 */
@Entity
@Table(name = "discount", catalog = "shopsbacker", uniqueConstraints = @UniqueConstraint(columnNames = {
		"NAME", "MERCHANT_ID" }))
public class Discount implements java.io.Serializable {

	private String discountId;
	private User userByCreatedby;
	private User userByUpdatedby;
	private Merchant merchant;
	private String name;
	private Double discountPercentage;
	private BigDecimal discountAmount;
	private Integer minQty;
	private Integer maxQty;
	private Date startDate;
	private Date endDate;
	private Date startTime;
	private Date endTime;
	private BigDecimal minAmount;
	private char isactive;
	private Date created;
	private Date updated;
	private List<Product> products = new ArrayList<Product>();

	public Discount() {
	}

	public Discount(String discountId, Merchant merchant, BigDecimal minAmount,
			char isactive) {
		this.discountId = discountId;
		this.merchant = merchant;
		this.minAmount = minAmount;
		this.isactive = isactive;
	}

	public Discount(String discountId, User userByCreatedby,
			User userByUpdatedby, Merchant merchant, String name,
			Double discountPercentage, BigDecimal discountAmount,
			Integer minQty, Integer maxQty, Date startDate, Date endDate,
			Date startTime, Date endTime, BigDecimal minAmount, char isactive,
			Date created, Date updated, List<Product> products) {
		this.discountId = discountId;
		this.userByCreatedby = userByCreatedby;
		this.userByUpdatedby = userByUpdatedby;
		this.merchant = merchant;
		this.name = name;
		this.discountPercentage = discountPercentage;
		this.discountAmount = discountAmount;
		this.minQty = minQty;
		this.maxQty = maxQty;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.minAmount = minAmount;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
		this.products = products;
	}

	@Id
	@Column(name = "DISCOUNT_ID", unique = true, nullable = false, length = 32)
	public String getDiscountId() {
		return this.discountId;
	}

	public void setDiscountId(String discountId) {
		this.discountId = discountId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATEDBY")
	public User getUserByCreatedby() {
		return this.userByCreatedby;
	}

	public void setUserByCreatedby(User userByCreatedby) {
		this.userByCreatedby = userByCreatedby;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UPDATEDBY")
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

	@Column(name = "NAME", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DISCOUNT_PERCENTAGE", precision = 22, scale = 0)
	public Double getDiscountPercentage() {
		return this.discountPercentage;
	}

	public void setDiscountPercentage(Double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	@Column(name = "DISCOUNT_AMOUNT", precision = 15)
	public BigDecimal getDiscountAmount() {
		return this.discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	@Column(name = "MIN_QTY")
	public Integer getMinQty() {
		return this.minQty;
	}

	public void setMinQty(Integer minQty) {
		this.minQty = minQty;
	}

	@Column(name = "MAX_QTY")
	public Integer getMaxQty() {
		return this.maxQty;
	}

	public void setMaxQty(Integer maxQty) {
		this.maxQty = maxQty;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "START_DATE", length = 19)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_DATE", length = 19)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "START_TIME", length = 8)
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "END_TIME", length = 8)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "MIN_AMOUNT", nullable = false, precision = 15)
	public BigDecimal getMinAmount() {
		return this.minAmount;
	}

	public void setMinAmount(BigDecimal minAmount) {
		this.minAmount = minAmount;
	}

	@Column(name = "ISACTIVE", nullable = false, length = 1)
	public char getIsactive() {
		return this.isactive;
	}

	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED", length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED", length = 19)
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "discount")
	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
