package com.mitosis.shopsbacker.model;

// Generated Nov 6, 2015 7:27:52 PM 

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * MyCart Created by Sundaram C.
 */
@Entity
@Table(name = "my_cart", catalog = "shopsbacker")
public class MyCart implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String myCartId;
	private ProductOfferline productOfferline;
	private Merchant merchant;
	private Product product;
	private Customer customerByCreatedby;
	private ProductOffer productOffer;
	private Customer customerByCustomerId;
	private Customer customerByUpdatedby;
	private Store store;
	private int qty;
	private char isactive;
	private Date created;
	private Date updated;

	public MyCart() {
	}

	public MyCart(String myCartId, Merchant merchant, Product product,
			Customer customerByCustomerId, Store store, int qty, char isactive) {
		this.myCartId = myCartId;
		this.merchant = merchant;
		this.product = product;
		this.customerByCustomerId = customerByCustomerId;
		this.store = store;
		this.qty = qty;
		this.isactive = isactive;
	}

	public MyCart(String myCartId, ProductOfferline productOfferline,
			Merchant merchant, Product product, Customer customerByCreatedby,
			ProductOffer productOffer, Customer customerByCustomerId,
			Customer customerByUpdatedby, Store store, int qty, char isactive,
			Date created, Date updated) {
		this.myCartId = myCartId;
		this.productOfferline = productOfferline;
		this.merchant = merchant;
		this.product = product;
		this.customerByCreatedby = customerByCreatedby;
		this.productOffer = productOffer;
		this.customerByCustomerId = customerByCustomerId;
		this.customerByUpdatedby = customerByUpdatedby;
		this.store = store;
		this.qty = qty;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	@Id
	@Column(name = "MY_CART_ID", unique = true, nullable = false, length = 32)
	public String getMyCartId() {
		return this.myCartId;
	}

	public void setMyCartId(String myCartId) {
		this.myCartId = myCartId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_OFFERLINE_ID")
	public ProductOfferline getProductOfferline() {
		return this.productOfferline;
	}

	public void setProductOfferline(ProductOfferline productOfferline) {
		this.productOfferline = productOfferline;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATEDBY")
	public Customer getCustomerByCreatedby() {
		return this.customerByCreatedby;
	}

	public void setCustomerByCreatedby(Customer customerByCreatedby) {
		this.customerByCreatedby = customerByCreatedby;
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
	@JoinColumn(name = "CUSTOMER_ID", nullable = false)
	public Customer getCustomerByCustomerId() {
		return this.customerByCustomerId;
	}

	public void setCustomerByCustomerId(Customer customerByCustomerId) {
		this.customerByCustomerId = customerByCustomerId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UPDATEDBY")
	public Customer getCustomerByUpdatedby() {
		return this.customerByUpdatedby;
	}

	public void setCustomerByUpdatedby(Customer customerByUpdatedby) {
		this.customerByUpdatedby = customerByUpdatedby;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STORE_ID", nullable = false)
	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
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
