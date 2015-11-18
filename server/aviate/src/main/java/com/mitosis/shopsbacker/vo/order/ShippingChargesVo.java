package com.mitosis.shopsbacker.vo.order;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.shopsbacker.model.Merchant;

@XmlRootElement
public class ShippingChargesVo {
	private String shippingChargesId;
	private Merchant merchant;
	private BigDecimal chargingAmount;
	private BigDecimal amountRange;
	public String getShippingChargesId() {
		return shippingChargesId;
	}
	public void setShippingChargesId(String shippingChargesId) {
		this.shippingChargesId = shippingChargesId;
	}
	public Merchant getMerchant() {
		return merchant;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
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

}
