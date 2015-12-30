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

import org.hibernate.annotations.GenericGenerator;

/**
 * ProductOfferLine Created by Sundaram C.
 */
@Entity
@Table(name = "product_offer_line")
public class ProductOfferLine implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String productOfferLineId;
	private String createdby;
	private String updatedby;
	private Product product;
	private ProductOffer productOffer;
	private double discountPercentage;
	private BigDecimal discountAmount;
	private char isactive;
	private Date created;
	private Date updated;
	private List<MyCart> myCarts = new ArrayList<MyCart>();

	public ProductOfferLine() {
	}

	public ProductOfferLine(String productOfferLineId, String createdby,
			String updatedby, Product product, ProductOffer productOffer,
			double discountPercentage,BigDecimal discountAmount, char isactive, Date updated) {
		this.productOfferLineId = productOfferLineId;
		this.createdby = createdby;
		this.updatedby = updatedby;
		this.product = product;
		this.productOffer = productOffer;
		this.discountPercentage = discountPercentage;
		this.discountAmount = discountAmount;
		this.isactive = isactive;
		this.updated = updated;
	}

	public ProductOfferLine(String productOfferLineId, String createdby,
			String updatedby, Product product, ProductOffer productOffer,
			double discountPercentage,BigDecimal discountAmount, char isactive, Date created,
			Date updated, List<MyCart> myCarts) {
		this.productOfferLineId = productOfferLineId;
		this.createdby = createdby;
		this.updatedby = updatedby;
		this.product = product;
		this.productOffer = productOffer;
		this.discountPercentage = discountPercentage;
		this.discountAmount = discountAmount;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
		this.myCarts = myCarts;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "PRODUCT_OFFER_LINE_ID", unique = true, nullable = false, length = 32)
	public String getProductOfferLineId() {
		return this.productOfferLineId;
	}

	public void setProductOfferLineId(String productOfferLineId) {
		this.productOfferLineId = productOfferLineId;
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
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_OFFER_ID", nullable = false)
	public ProductOffer getProductOffer() {
		return this.productOffer;
	}

	public void setProductOffer(ProductOffer productOffer) {
		this.productOffer = productOffer;
	}

	@Column(name = "DISCOUNT_PERCENTAGE", nullable = false, precision = 22, scale = 0)
	public double getDiscountPercentage() {
		return this.discountPercentage;
	}

	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	
	@Column(name = "DISCOUNT_AMOUNT", precision = 22, scale = 0)
	public BigDecimal getDiscountAmount() {
		return this.discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
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
	@Column(name = "UPDATED", nullable = false, length = 19)
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productOfferLine")
	public List<MyCart> getMyCarts() {
		return this.myCarts;
	}

	public void setMyCarts(List<MyCart> myCarts) {
		this.myCarts = myCarts;
	}

}
