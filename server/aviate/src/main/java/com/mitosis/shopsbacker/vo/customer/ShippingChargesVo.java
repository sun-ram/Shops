package com.mitosis.shopsbacker.vo.customer;

// Generated Nov 12, 2015 6:16:19 PM 

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.shopsbacker.vo.admin.MerchantVo;

/**
 * ShippingCharges Created by Sundaram C.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ShippingChargesVo implements java.io.Serializable {

	private String shippingChargesId;
	private UserVo userByUpdatedby;
	private UserVo userByCreatedby;
	private MerchantVo merchant;
	private BigDecimal chargingAmount;
	private BigDecimal amountRange;
	private char isactive;
	private Date created;
	private Date updated;
	public String getShippingChargesId() {
		return shippingChargesId;
	}
	public void setShippingChargesId(String shippingChargesId) {
		this.shippingChargesId = shippingChargesId;
	}
	public UserVo getUserByUpdatedby() {
		return userByUpdatedby;
	}
	public void setUserByUpdatedby(UserVo userByUpdatedby) {
		this.userByUpdatedby = userByUpdatedby;
	}
	public UserVo getUserByCreatedby() {
		return userByCreatedby;
	}
	public void setUserByCreatedby(UserVo userByCreatedby) {
		this.userByCreatedby = userByCreatedby;
	}
	public MerchantVo getMerchant() {
		return merchant;
	}
	public void setMerchant(MerchantVo merchant) {
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

}
