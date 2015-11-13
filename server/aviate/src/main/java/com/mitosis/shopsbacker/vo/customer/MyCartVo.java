package com.mitosis.shopsbacker.vo.customer;

// Generated Nov 12, 2015 6:16:19 PM 

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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.shopsbacker.vo.admin.MerchantVo;

/**
 * MyCart Created by Sundaram C.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class MyCartVo implements java.io.Serializable {

	private String myCartId;
	private MerchantVo merchant;
	private ProductVo product;
	private CustomerVo customerByCreatedby;
	private ProductOfferVo productOffer;
	private CustomerVo customerByCustomerId;
	private CustomerVo customerByUpdatedby;
	private StoreVo store;
	private ProductOfferLineVo productOfferLine;
	private int qty;
	private char isactive;
	private Date created;
	private Date updated;
	public String getMyCartId() {
		return myCartId;
	}
	public void setMyCartId(String myCartId) {
		this.myCartId = myCartId;
	}
	public MerchantVo getMerchant() {
		return merchant;
	}
	public void setMerchant(MerchantVo merchant) {
		this.merchant = merchant;
	}
	public ProductVo getProduct() {
		return product;
	}
	public void setProduct(ProductVo product) {
		this.product = product;
	}
	public CustomerVo getCustomerByCreatedby() {
		return customerByCreatedby;
	}
	public void setCustomerByCreatedby(CustomerVo customerByCreatedby) {
		this.customerByCreatedby = customerByCreatedby;
	}
	public ProductOfferVo getProductOffer() {
		return productOffer;
	}
	public void setProductOffer(ProductOfferVo productOffer) {
		this.productOffer = productOffer;
	}
	public CustomerVo getCustomerByCustomerId() {
		return customerByCustomerId;
	}
	public void setCustomerByCustomerId(CustomerVo customerByCustomerId) {
		this.customerByCustomerId = customerByCustomerId;
	}
	public CustomerVo getCustomerByUpdatedby() {
		return customerByUpdatedby;
	}
	public void setCustomerByUpdatedby(CustomerVo customerByUpdatedby) {
		this.customerByUpdatedby = customerByUpdatedby;
	}
	public StoreVo getStore() {
		return store;
	}
	public void setStore(StoreVo store) {
		this.store = store;
	}
	public ProductOfferLineVo getProductOfferLine() {
		return productOfferLine;
	}
	public void setProductOfferLine(ProductOfferLineVo productOfferLine) {
		this.productOfferLine = productOfferLine;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public char getIsactive() {
		return isactive;
	}
	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}


}
