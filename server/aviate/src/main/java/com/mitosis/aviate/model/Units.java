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
@Table(name = "units")
public class Units {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="units_id")
	private Long unitsId;
	@Column(name="merchant_id")
	private Long merchantId;
	@Column(name="store_id")
	private Long storeId;
	@Column(name="abbreviation")
	private String abbreviation;
	@Column (name="description")
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
