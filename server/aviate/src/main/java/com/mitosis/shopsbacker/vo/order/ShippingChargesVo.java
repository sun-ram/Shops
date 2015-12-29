package com.mitosis.shopsbacker.vo.order;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;

@XmlRootElement
public class ShippingChargesVo {
	private String shippingChargesId;
	private MerchantVo merchantVo;
	private BigDecimal chargingAmount;
	private BigDecimal amountRange;
	private String userId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getShippingChargesId() {
		return shippingChargesId;
	}
	public void setShippingChargesId(String shippingChargesId) {
		this.shippingChargesId = shippingChargesId;
	}
	public BigDecimal getChargingAmount() {
		return chargingAmount;
	}
	public void setChargingAmount(BigDecimal chargingAmount) {
		this.chargingAmount = chargingAmount;
	}
	public BigDecimal getAmountRange() {
		return amountRange;
	}
	public void setAmountRange(BigDecimal amountRange) {
		this.amountRange = amountRange;
	}
	public MerchantVo getMerchantVo() {
		return merchantVo;
	}
	public void setMerchantVo(MerchantVo merchantVo) {
		this.merchantVo = merchantVo;
	}
	

}
