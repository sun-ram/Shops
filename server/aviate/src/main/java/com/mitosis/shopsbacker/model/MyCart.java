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

import org.hibernate.annotations.GenericGenerator;

/**
 * MyCart Created by Sundaram C.
 */
@Entity
@Table(name = "my_cart")
public class MyCart implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String myCartId;
	private Merchant merchant;
	private Product product;
	private String createdby;
	private ProductOffer productOffer;
	private Customer customer;
	private Discount discount;
	private String updatedby;
	private Store store;
	private ProductOfferLine productOfferLine;
	private int qty;
	private char isactive;
	private Date created;
	private Date updated;

	public MyCart() {
	}

	public MyCart(String myCartId, Merchant merchant, Product product,
			Customer customer, Store store, int qty, char isactive) {
		this.myCartId = myCartId;
		this.merchant = merchant;
		this.product = product;
		this.customer = customer;
		this.store = store;
		this.qty = qty;
		this.isactive = isactive;
	}

	public MyCart(String myCartId, Merchant merchant, Product product,
			String createdby, ProductOffer productOffer,Discount discount,
			Customer customer, String updatedby,
			Store store, ProductOfferLine productOfferLine, int qty,
			char isactive, Date created, Date updated) {
		this.myCartId = myCartId;
		this.merchant = merchant;
		this.product = product;
		this.createdby = createdby;
		this.productOffer = productOffer;
		this.discount = discount;
		this.customer = customer;
		this.updatedby = updatedby;
		this.store = store;
		this.productOfferLine = productOfferLine;
		this.qty = qty;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "MY_CART_ID", unique = true, nullable = false, length = 32)
	public String getMyCartId() {
		return this.myCartId;
	}

	public void setMyCartId(String myCartId) {
		this.myCartId = myCartId;
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

	public String getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_OFFER_ID")
	public ProductOffer getProductOffer() {
		return this.productOffer;
	}

	public void setProductOffer(ProductOffer productOffer) {
		this.productOffer = productOffer;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DISCOUNT_ID")
	public Discount getDiscount() {
		return this.discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_OFFER_LINE_ID")
	public ProductOfferLine getProductOfferLine() {
		return this.productOfferLine;
	}

	public void setProductOfferLine(ProductOfferLine productOfferLine) {
		this.productOfferLine = productOfferLine;
	}

	@Column(name = "QTY", nullable = false)
	public int getQty() {
		return this.qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
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

}
