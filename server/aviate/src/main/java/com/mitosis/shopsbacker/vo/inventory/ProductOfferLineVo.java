package com.mitosis.shopsbacker.vo.inventory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.mitosis.shopsbacker.vo.customer.MyCartVo;


public class ProductOfferLineVo {
	
	private String productOfferLineId;
	private ProductVo productVo;
	private ProductOfferVo productOfferVo;
	private double discountPercentage;
	private BigDecimal discountAmount;
	private List<MyCartVo> myCartsVo = new ArrayList<MyCartVo>();
	public String getProductOfferLineId() {
		return productOfferLineId;
	}
	public void setProductOfferLineId(String productOfferLineId) {
		this.productOfferLineId = productOfferLineId;
	}
	public ProductVo getProductVo() {
		return productVo;
	}
	public void setProductVo(ProductVo productVo) {
		this.productVo = productVo;
	}
	public ProductOfferVo getProductOfferVo() {
		return productOfferVo;
	}
	public void setProductOfferVo(ProductOfferVo productOfferVo) {
		this.productOfferVo = productOfferVo;
	}
	public double getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	public List<MyCartVo> getMyCartsVo() {
		return myCartsVo;
	}
	public void setMyCartsVo(List<MyCartVo> myCartsVo) {
		this.myCartsVo = myCartsVo;
	}
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}
	
	
}
