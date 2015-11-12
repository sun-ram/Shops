package com.mitosis.shopsbacker.model;

// Generated Nov 12, 2015 6:16:19 PM 

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
 * ProductOfferLine Created by Sundaram C.
 */
@Entity
@Table(name = "product_offer_line", catalog = "shopsbacker")
public class ProductOfferLine implements java.io.Serializable {

	private String productOfferLineId;
	private User userByCreatedby;
	private User userByUpdatedby;
	private Product product;
	private ProductOffer productOffer;
	private double discountPercentage;
	private char isactive;
	private Date created;
	private Date updated;
	private Set myCarts = new HashSet(0);

	public ProductOfferLine() {
	}

	public ProductOfferLine(String productOfferLineId, User userByCreatedby,
			User userByUpdatedby, Product product, ProductOffer productOffer,
			double discountPercentage, char isactive, Date updated) {
		this.productOfferLineId = productOfferLineId;
		this.userByCreatedby = userByCreatedby;
		this.userByUpdatedby = userByUpdatedby;
		this.product = product;
		this.productOffer = productOffer;
		this.discountPercentage = discountPercentage;
		this.isactive = isactive;
		this.updated = updated;
	}

	public ProductOfferLine(String productOfferLineId, User userByCreatedby,
			User userByUpdatedby, Product product, ProductOffer productOffer,
			double discountPercentage, char isactive, Date created,
			Date updated, Set myCarts) {
		this.productOfferLineId = productOfferLineId;
		this.userByCreatedby = userByCreatedby;
		this.userByUpdatedby = userByUpdatedby;
		this.product = product;
		this.productOffer = productOffer;
		this.discountPercentage = discountPercentage;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
		this.myCarts = myCarts;
	}

	@Id
	@Column(name = "PRODUCT_OFFER_LINE_ID", unique = true, nullable = false, length = 32)
	public String getProductOfferLineId() {
		return this.productOfferLineId;
	}

	public void setProductOfferLineId(String productOfferLineId) {
		this.productOfferLineId = productOfferLineId;
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
	public Set getMyCarts() {
		return this.myCarts;
	}

	public void setMyCarts(Set myCarts) {
		this.myCarts = myCarts;
	}

}
