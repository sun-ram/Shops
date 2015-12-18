/**
 * 
 */
package com.mitosis.shopsbacker.vo.admin;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Anbukkani Gajendiran
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class StoreHolidayVo {

	private String storeHolidayId;
	private String reason;
	private MerchantVo merchant;
	private StoreVo store;
	private String holidayDate;
	
	public String getStoreHolidayId() {
		return storeHolidayId;
	}
	public void setStoreHolidayId(String storeHolidayId) {
		this.storeHolidayId = storeHolidayId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public MerchantVo getMerchant() {
		return merchant;
	}
	public void setMerchant(MerchantVo merchant) {
		this.merchant = merchant;
	}
	public StoreVo getStore() {
		return store;
	}
	public void setStore(StoreVo store) {
		this.store = store;
	}
	public String getHolidayDate() {
		return holidayDate;
	}
	public void setHolidayDate(String holidayDate) {
		this.holidayDate = holidayDate;
	}
	
}
