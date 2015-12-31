/**
 * 
 */
package com.mitosis.shopsbacker.vo.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	private String holidayReasons;
	private List<Date> holidayDates= new ArrayList<Date>();
	private String userId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
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
	public String getHolidayReasons() {
		return holidayReasons;
	}
	public void setHolidayReasons(String holidayReasons) {
		this.holidayReasons = holidayReasons;
	}
	public List<Date> getHolidayDates() {
		return holidayDates;
	}
	public void setHolidayDates(List<Date> holidayDates) {
		this.holidayDates = holidayDates;
	}
}
