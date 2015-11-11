package com.mitosis.shopsbacker.vo.customer;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class UnitsVo {

	private Long unitsId;
	private Long merchantId;
	private Long storeId;
	private String abbreviation;
	private String description;
	
	public Long getUnitsId() {
		return unitsId;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public String getDescription() {
		return description;
	}
	public void setUnitsId(Long unitsId) {
		this.unitsId = unitsId;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	
	
}
