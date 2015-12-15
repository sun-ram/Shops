package com.mitosis.shopsbacker.vo.inventory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mitosis.shopsbacker.vo.admin.MerchantVo;
import com.mitosis.shopsbacker.vo.customer.MyCartVo;

public class ProductOfferVo {

	private String productOfferId;
	private MerchantVo merchantVo;
	private ProductVo productVo;
	private String name;
	private String description;
	private int qty;
	private BigDecimal offerAmount;
	private Date fromDate;
	private Date todate;
	private Date startTime;
	private Date endTime;
	private List<ProductOfferLineVo> productOfferLinesVo = new ArrayList<ProductOfferLineVo>();
	private List<MyCartVo> myCartsVo = new ArrayList<MyCartVo>();
	
	public String getProductOfferId() {
		return productOfferId;
	}
	public void setProductOfferId(String productOfferId) {
		this.productOfferId = productOfferId;
	}
	public MerchantVo getMerchantVo() {
		return merchantVo;
	}
	public void setMerchantVo(MerchantVo merchantVo) {
		this.merchantVo = merchantVo;
	}
	public ProductVo getProductVo() {
		return productVo;
	}
	public void setProductVo(ProductVo productVo) {
		this.productVo = productVo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public BigDecimal getOfferAmount() {
		return offerAmount;
	}
	public void setOfferAmount(BigDecimal offerAmount) {
		this.offerAmount = offerAmount;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getTodate() {
		return todate;
	}
	public void setTodate(Date todate) {
		this.todate = todate;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public List<ProductOfferLineVo> getProductOfferLinesVo() {
		return productOfferLinesVo;
	}
	public void setProductOfferLinesVo(List<ProductOfferLineVo> productOfferLinesVo) {
		this.productOfferLinesVo = productOfferLinesVo;
	}
	public List<MyCartVo> getMyCartsVo() {
		return myCartsVo;
	}
	public void setMyCartsVo(List<MyCartVo> myCartsVo) {
		this.myCartsVo = myCartsVo;
	}
	
	
}
