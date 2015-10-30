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
@Table(name = "product_uom")
public class ProductUOM {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="uom_id")
	private Long unitOfMeasureId;
	@Column(name="product_id")
	private Long productId;
	@Column(name="abbreviation")
	private String abbreviation;
	@Column(name="description")
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
