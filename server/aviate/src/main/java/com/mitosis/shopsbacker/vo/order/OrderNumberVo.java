package com.mitosis.shopsbacker.vo.order;

import com.mitosis.shopsbacker.vo.admin.MerchantVo;
import com.mitosis.shopsbacker.vo.admin.StoreVo;

/**
 * @author kathir
 *
 */
public class OrderNumberVo {

	private String orderNumberId;
	private MerchantVo merchant;
	private StoreVo store;
	private int startingNumber;
	private String prefix;
	private String suffix;
	private Integer nextNumber;
	private String userId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrderNumberId() {
		return orderNumberId;
	}
	public void setOrderNumberId(String orderNumberId) {
		this.orderNumberId = orderNumberId;
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
	public int getStartingNumber() {
		return startingNumber;
	}
	public void setStartingNumber(int startingNumber) {
		this.startingNumber = startingNumber;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public Integer getNextNumber() {
		return nextNumber;
	}
	public void setNextNumber(Integer nextNumber) {
		this.nextNumber = nextNumber;
	}
	
}
