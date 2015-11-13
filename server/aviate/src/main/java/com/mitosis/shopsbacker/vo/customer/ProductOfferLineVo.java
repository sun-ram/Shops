package com.mitosis.shopsbacker.vo.customer;

// Generated Nov 12, 2015 6:16:19 PM 

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * ProductOfferLine Created by Sundaram C.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ProductOfferLineVo implements java.io.Serializable {

	private String productOfferLineId;
	private UserVo userByCreatedby;
	private UserVo userByUpdatedby;
	private ProductVo product;
	private ProductOfferVo productOffer;
	private double discountPercentage;
	private char isactive;
	private Date created;
	private Date updated;
	private List<MyCartVo> myCarts = new ArrayList<MyCartVo>();
	public String getProductOfferLineId() {
		return productOfferLineId;
	}
	public void setProductOfferLineId(String productOfferLineId) {
		this.productOfferLineId = productOfferLineId;
	}
	public UserVo getUserByCreatedby() {
		return userByCreatedby;
	}
	public void setUserByCreatedby(UserVo userByCreatedby) {
		this.userByCreatedby = userByCreatedby;
	}
	public UserVo getUserByUpdatedby() {
		return userByUpdatedby;
	}
	public void setUserByUpdatedby(UserVo userByUpdatedby) {
		this.userByUpdatedby = userByUpdatedby;
	}
	public ProductVo getProduct() {
		return product;
	}
	public void setProduct(ProductVo product) {
		this.product = product;
	}
	public ProductOfferVo getProductOffer() {
		return productOffer;
	}
	public void setProductOffer(ProductOfferVo productOffer) {
		this.productOffer = productOffer;
	}
	public double getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
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
	public List<MyCartVo> getMyCarts() {
		return myCarts;
	}
	public void setMyCarts(List<MyCartVo> myCarts) {
		this.myCarts = myCarts;
	}

}
