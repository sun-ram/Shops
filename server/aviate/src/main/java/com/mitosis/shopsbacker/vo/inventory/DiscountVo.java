package com.mitosis.shopsbacker.vo.inventory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.shopsbacker.vo.admin.MerchantVo;
import com.mitosis.shopsbacker.vo.admin.StoreVo;

/**
 * @author Riyaz Khan
 *
 */
@XmlRootElement
public class DiscountVo {
	
	private String discountId;
	private MerchantVo merchantVo;
	private String name;
	private Double discountPercentage;
	private BigDecimal discountAmount;
	private Integer minQty;
	private Integer maxQty;
	private Date startDate;
	private Date endDate;
	private Date startTime;
	private Date endTime;
	private BigDecimal minAmount;
	private List<ProductVo> productsVo = new ArrayList<ProductVo>();
	private List<StoreVo> storesVo = new ArrayList<StoreVo>();
	
	public String getDiscountId() {
		return discountId;
	}
	public void setDiscountId(String discountId) {
		this.discountId = discountId;
	}
	public MerchantVo getMerchantVo() {
		return merchantVo;
	}
	public void setMerchantVo(MerchantVo merchantVo) {
		this.merchantVo = merchantVo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(Double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}
	public Integer getMinQty() {
		return minQty;
	}
	public void setMinQty(Integer minQty) {
		this.minQty = minQty;
	}
	public Integer getMaxQty() {
		return maxQty;
	}
	public void setMaxQty(Integer maxQty) {
		this.maxQty = maxQty;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	public BigDecimal getMinAmount() {
		return minAmount;
	}
	public void setMinAmount(BigDecimal minAmount) {
		this.minAmount = minAmount;
	}
	public List<ProductVo> getProductsVo() {
		return productsVo;
	}
	public void setProductsVo(List<ProductVo> productsVo) {
		this.productsVo = productsVo;
	}
	public List<StoreVo> getStoresVo() {
		return storesVo;
	}
	public void setStoresVo(List<StoreVo> storesVo) {
		this.storesVo = storesVo;
	}
	
	

}
