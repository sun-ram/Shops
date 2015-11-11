package com.mitosis.shopsbacker.vo.customer;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.shopsbacker.vo.BaseVO;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CustomerProductVo extends BaseVO {
	private Long myListId;
	private Long productId;
	private Long customerId;
	private Long storeId;
	private boolean active;
	public Long getMyListId() {
		return myListId;
	}
	public void setMyListId(Long myListId) {
		this.myListId = myListId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
