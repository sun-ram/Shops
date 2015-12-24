package com.mitosis.shopsbacker.vo.inventory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mitosis.shopsbacker.vo.admin.MerchantVo;
import com.mitosis.shopsbacker.vo.admin.StoreVo;
import com.mitosis.shopsbacker.vo.customer.MyCartVo;

public class ProductOfferVo {

	private String productOfferId;
	private MerchantVo merchant;
	private StoreVo store;
	private ProductVo product;
	private String name;
	private String description;
	private int qty;
	private BigDecimal offerAmount;
	private Date fromDate;
	private Date todate;
	private Date startTime;
	private Date endTime;
	private List<StoreVo> storeList = new ArrayList<StoreVo>();
	private List<ProductOfferLineVo> productOfferLines = new ArrayList<ProductOfferLineVo>();
	private List<MyCartVo> myCarts = new ArrayList<MyCartVo>();
	
	public String getProductOfferId() {
		return productOfferId;
	}
	public void setProductOfferId(String productOfferId) {
		this.productOfferId = productOfferId;
	}
	public MerchantVo getMerchantVo() {
		return merchant;
	}
	public void setMerchantVo(MerchantVo merchantVo) {
		this.merchant = merchantVo;
	}
	public ProductVo getProductVo() {
		return product;
	}
	public void setProductVo(ProductVo productVo) {
		this.product = productVo;
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
	public List<ProductOfferLineVo> getProductOfferLinesVo() {
		return productOfferLines;
	}
	public void setProductOfferLinesVo(List<ProductOfferLineVo> productOfferLinesVo) {
		this.productOfferLines = productOfferLinesVo;
	}
	public List<MyCartVo> getMyCartsVo() {
		return myCarts;
	}
	public void setMyCartsVo(List<MyCartVo> myCartsVo) {
		this.myCarts = myCartsVo;
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
	public StoreVo getStore() {
		return store;
	}
	public void setStore(StoreVo store) {
		this.store = store;
	}
	public List<StoreVo> getStoreList() {
		return storeList;
	}
	public void setStoreList(List<StoreVo> storeList) {
		this.storeList = storeList;
	}
}
