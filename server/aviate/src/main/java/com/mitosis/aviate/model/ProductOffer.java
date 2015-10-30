package com.mitosis.aviate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "product_offers")
public class ProductOffer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_offer_id")
	private Long productOfferId;
	@Column(name="offer_name")
	private String offerName;
	@Column(name="store_id")
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
