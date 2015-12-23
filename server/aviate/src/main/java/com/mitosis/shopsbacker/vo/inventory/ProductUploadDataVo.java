package com.mitosis.shopsbacker.vo.inventory;

import java.math.BigDecimal;

public class ProductUploadDataVo {
	
	private String name;
	
	private String productCategory;
	
	private String productType;
	
	private String productMeasurement;
	
	private BigDecimal productUnit;
	
	private String edibleType;
	
	private BigDecimal wasPrice;
	
	private BigDecimal sellingPrice;
	
	private String brand;
	
	private String reason;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductMeasurement() {
		return productMeasurement;
	}

	public void setProductMeasurement(String productMeasurement) {
		this.productMeasurement = productMeasurement;
	}

	public BigDecimal getProductUnit() {
		return productUnit;
	}

	public void setProductUnit(BigDecimal productUnit) {
		this.productUnit = productUnit;
	}

	public String getEdibleType() {
		return edibleType;
	}

	public void setEdibleType(String edibleType) {
		this.edibleType = edibleType;
	}

	public BigDecimal getWasPrice() {
		return wasPrice;
	}

	public void setWasPrice(BigDecimal wasPrice) {
		this.wasPrice = wasPrice;
	}

	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	

}
