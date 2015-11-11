package com.mitosis.shopsbacker.vo.customer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ProductUOMVo {
	private Long unitOfMeasureId;
	private Long productId;
	private String abbreviation;
	private String description;

	public Long getUnitOfMeasureId() {
		return unitOfMeasureId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setUnitOfMeasureId(Long unitOfMeasureId) {
		this.unitOfMeasureId = unitOfMeasureId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public String getDescription() {
		return description;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
