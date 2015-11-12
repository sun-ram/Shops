package com.mitosis.shopsbacker.model;

// Generated Nov 6, 2015 7:27:52 PM 

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ShippingCharges Created by Sundaram C.
 */
@Entity
@Table(name = "shipping_charges", catalog = "shopsbacker")
public class ShippingCharges implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String shippingChargesId;
	private BigDecimal chargingAmount;
	private BigDecimal amountRange;
	private String merchantId;

	public ShippingCharges() {
	}

	public ShippingCharges(String shippingChargesId, BigDecimal chargingAmount,
			String merchantId) {
		this.shippingChargesId = shippingChargesId;
		this.chargingAmount = chargingAmount;
		this.merchantId = merchantId;
	}

	public ShippingCharges(String shippingChargesId, BigDecimal chargingAmount,
			BigDecimal amountRange, String merchantId) {
		this.shippingChargesId = shippingChargesId;
		this.chargingAmount = chargingAmount;
		this.amountRange = amountRange;
		this.merchantId = merchantId;
	}

	@Id
	@Column(name = "SHIPPING_CHARGES_ID", unique = true, nullable = false, length = 32)
	public String getShippingChargesId() {
		return this.shippingChargesId;
	}

	public void setShippingChargesId(String shippingChargesId) {
		this.shippingChargesId = shippingChargesId;
	}

	@Column(name = "CHARGING_AMOUNT", nullable = false, precision = 15)
	public BigDecimal getChargingAmount() {
		return this.chargingAmount;
	}

	public void setChargingAmount(BigDecimal chargingAmount) {
		this.chargingAmount = chargingAmount;
	}

	@Column(name = "AMOUNT_RANGE", precision = 15)
	public BigDecimal getAmountRange() {
		return this.amountRange;
	}

	public void setAmountRange(BigDecimal amountRange) {
		this.amountRange = amountRange;
	}

	@Column(name = "MERCHANT_ID", nullable = false, length = 32)
	public String getMerchantId() {
		return this.merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

}
