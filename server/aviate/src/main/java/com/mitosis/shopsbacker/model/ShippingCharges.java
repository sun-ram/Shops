package com.mitosis.shopsbacker.model;

// Generated Nov 12, 2015 6:16:19 PM 

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * ShippingCharges Created by Sundaram C.
 */
@Entity
@Table(name = "shipping_charges")
public class ShippingCharges implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String shippingChargesId;
	private Merchant merchant;
	private BigDecimal chargingAmount;
	private BigDecimal amountRange;
	private char isactive;
	private Date created;
	private String createdby;
	private Date updated;
	private String updatedby;


	public ShippingCharges() {
	}

	public ShippingCharges(String shippingChargesId, String updatedby,
			String createdby, Merchant merchant, BigDecimal chargingAmount,
			char isactive, Date created, Date updated) {
		this.shippingChargesId = shippingChargesId;
		this.updatedby = updatedby;
		this.createdby = createdby;
		this.merchant = merchant;
		this.chargingAmount = chargingAmount;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	public ShippingCharges(String shippingChargesId, String updatedby,
			String createdby, Merchant merchant, BigDecimal chargingAmount,
			BigDecimal amountRange, char isactive, Date created, Date updated) {
		this.shippingChargesId = shippingChargesId;
		this.updatedby = updatedby;
		this.createdby = createdby;
		this.merchant = merchant;
		this.chargingAmount = chargingAmount;
		this.amountRange = amountRange;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "SHIPPING_CHARGES_ID", unique = true, nullable = false, length = 32)
	public String getShippingChargesId() {
		return this.shippingChargesId;
	}

	public void setShippingChargesId(String shippingChargesId) {
		this.shippingChargesId = shippingChargesId;
	}

	@Column(name = "UPDATEDBY", length = 32)
	public String getUpdatedby() {
		return this.updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	@Column(name = "CREATEDBY", length = 32)
	public String getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MERCHANT_ID", nullable = false)
	public Merchant getMerchant() {
		return this.merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
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

	@Column(name = "ISACTIVE", nullable = false, length = 1)
	public char getIsactive() {
		return this.isactive;
	}

	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED", nullable = false, length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED", nullable = false, length = 19)
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}
