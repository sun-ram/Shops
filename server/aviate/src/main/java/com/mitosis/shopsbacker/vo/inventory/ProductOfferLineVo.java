package com.mitosis.shopsbacker.vo.inventory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.mitosis.shopsbacker.vo.admin.StoreVo;
import com.mitosis.shopsbacker.vo.customer.MyCartVo;


public class ProductOfferLineVo {
	
	private String productOfferLineId;
	private ProductVo product;
	private ProductOfferVo productOffer;
	private double discountPercentage;
	private BigDecimal discountAmount;
	private List<ProductOfferVo> productOfferList = new ArrayList<ProductOfferVo>();
	private List<MyCartVo> myCarts = new ArrayList<MyCartVo>();
	
	public String getProductOfferLineId() {
		return productOfferLineId;
	}
	public void setProductOfferLineId(String productOfferLineId) {
		this.productOfferLineId = productOfferLineId;
	}
	public ProductVo getProductVo() {
		return product;
	}
	public void setProductVo(ProductVo productVo) {
		this.product = productVo;
	}
	public ProductOfferVo getProductOfferVo() {
		return productOffer;
	}
	public void setProductOfferVo(ProductOfferVo productOfferVo) {
		this.productOffer = productOfferVo;
	}
	public double getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	public List<MyCartVo> getMyCartsVo() {
		return myCarts;
	}
	public void setMyCartsVo(List<MyCartVo> myCartsVo) {
		this.myCarts = myCartsVo;
	}
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}
	public List<ProductOfferVo> getProductOfferList() {
		return productOfferList;
	}
	public void setProductOfferList(List<ProductOfferVo> productOfferList) {
		this.productOfferList = productOfferList;
	}
	
}
