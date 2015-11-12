package com.mitosis.shopsbacker.model;

// Generated Nov 6, 2015 7:27:52 PM 

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String discountId;
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
	private String merchantId;
	private List<Product> products = new ArrayList<Product>();

	public Discount() {
	}

	public Discount(String discountId, BigDecimal minAmount, String merchantId) {
		this.discountId = discountId;
		this.minAmount = minAmount;
		this.merchantId = merchantId;
	}

	public Discount(String discountId, String name, Double discountPercentage,
			BigDecimal discountAmount, Integer minQty, Integer maxQty,
			Date startDate, Date endDate, Date startTime, Date endTime,
			BigDecimal minAmount, String merchantId, List<Product> products) {
		this.discountId = discountId;
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
		this.merchantId = merchantId;
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

	@Column(name = "MERCHANT_ID", nullable = false, length = 32)
	public String getMerchantId() {
		return this.merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "discount")
	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
