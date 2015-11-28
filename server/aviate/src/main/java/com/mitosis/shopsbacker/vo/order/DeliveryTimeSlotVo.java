/**
 * 
 */
package com.mitosis.shopsbacker.vo.order;

import java.util.Date;

import com.mitosis.shopsbacker.vo.admin.MerchantVo;

/**
 * @author Anbukkani Gajendiran
 *
 */
public class DeliveryTimeSlotVo {
	
	private String deliveryTimeSlotId;
	private MerchantVo merchant;
	private Date fromTime;
	private Date toTime;
	private String storeId;
	
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getDeliveryTimeSlotId() {
		return deliveryTimeSlotId;
	}
	public void setDeliveryTimeSlotId(String deliveryTimeSlotId) {
		this.deliveryTimeSlotId = deliveryTimeSlotId;
	}
	public MerchantVo getMerchant() {
		return merchant;
	}
	public void setMerchant(MerchantVo merchant) {
		this.merchant = merchant;
	}
	public Date getFromTime() {
		return fromTime;
	}
	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}
	public Date getToTime() {
		return toTime;
	}
	public void setToTime(Date toTime) {
		this.toTime = toTime;
	}
	
}
