package com.mitosis.shopsbacker.model;

// Generated Nov 12, 2015 6:16:19 PM 

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

/**
 * ProductOffer Created by Sundaram C.
 */
@Entity
@Table(name = "product_offer", uniqueConstraints = @UniqueConstraint(columnNames = {
		"NAME", "STORE_ID" }))
public class ProductOffer implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String productOfferId;
	private String createdby;
	private String updatedby;
	private Merchant merchant;
	private Store store;
	private Product product;
	private String name;
	private String description;
	private int qty;
	private BigDecimal offerAmount;
	private Date fromDate;
	private Date todate;
	private Date startTime;
	private Date endTime;
	private char isactive;
	private Date created;
	private Date updated;
	private List<ProductOfferLine> productOfferLines = new ArrayList<ProductOfferLine>();
	private List<MyCart> myCarts = new ArrayList<MyCart>();

	public ProductOffer() {
	}

	public ProductOffer(String productOfferId, String createdby,
			String updatedby, Merchant merchant,Store store, Product product,
			String name,int qty,BigDecimal offerAmount, Date fromDate, Date todate, Date startTime,
			Date endTime, char isactive, Date created, Date updated) {
		this.productOfferId = productOfferId;
		this.createdby = createdby;
		this.updatedby = updatedby;
		this.merchant = merchant;
		this.store = store;
		this.product = product;
		this.name = name;
		this.qty = qty;
		this.offerAmount = offerAmount;
		this.fromDate = fromDate;
		this.todate = todate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	public ProductOffer(String productOfferId, String createdby,
			String updatedby, Merchant merchant, Store store,Product product,
			String name, String description,int qty,BigDecimal offerAmount, Date fromDate, Date todate,
			Date startTime, Date endTime, char isactive, Date created,
			Date updated, List<ProductOfferLine> productOfferLines, List<MyCart> myCarts) {
		this.productOfferId = productOfferId;
		this.createdby = createdby;
		this.updatedby = updatedby;
		this.merchant = merchant;
		this.store = store;
		this.product = product;
		this.name = name;
		this.description = description;
		this.qty = qty;
		this.offerAmount = offerAmount;
		this.fromDate = fromDate;
		this.todate = todate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
		this.productOfferLines = productOfferLines;
		this.myCarts = myCarts;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "PRODUCT_OFFER_ID", unique = true, nullable = false, length = 32)
	public String getProductOfferId() {
		return this.productOfferId;
	}

	public void setProductOfferId(String productOfferId) {
		this.productOfferId = productOfferId;
	}

	public String getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getUpdatedby() {
		return this.updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
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
	
	@Column(name = "QTY")
	public int getQty() {
		return this.qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
	@Column(name = "OFFER_AMOUNT")
	public BigDecimal getOfferAmount() {
		return this.offerAmount;
	}
	
	public void setOfferAmount(BigDecimal offerAmount) {
		this.offerAmount = offerAmount;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FROM_DATE", length = 19)
	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TODATE", length = 19)
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
	@Cascade({CascadeType.DELETE})
	public List<ProductOfferLine> getProductOfferLines() {
		return this.productOfferLines;
	}

	public void setProductOfferLines(List<ProductOfferLine> productOfferLines) {
		this.productOfferLines = productOfferLines;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productOffer")
	public List<MyCart> getMyCarts() {
		return this.myCarts;
	}

	public void setMyCarts(List<MyCart> myCarts) {
		this.myCarts = myCarts;
	}

}
