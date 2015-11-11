package com.mitosis.shopsbacker.vo.customer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ProductOfferVo {
	private Long productOfferId;
	private String offerName;
	private Long storeId;
	
	public Long getProductOfferId() {
		return productOfferId;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setProductOfferId(Long productOfferId) {
		this.productOfferId = productOfferId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public String getOfferName() {
		return offerName;
	}
	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}
}
