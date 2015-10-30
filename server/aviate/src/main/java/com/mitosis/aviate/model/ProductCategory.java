package com.mitosis.aviate.model;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@XmlRootElement
@Entity
@Table(name = "product_category")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class ProductCategory implements Serializable  {
	private static final long serialVersionUID = -5188662426062165691L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="category_id")
	private Long categoryId;
	@Column(name="parent_category_id")
	private Long parentCategoryId;
	@Column(name="store_id")
	private Long storeId;
	@Column(name="category_name")
	private String categoryName;
	@Column(name="merchant_id")
	private Long merchantId;

	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="parent_category_id",insertable=false,updatable=false)
	private ProductCategory parentCategory;
	
	@OneToMany(mappedBy="parentCategory",fetch=FetchType.EAGER)
	private List<ProductCategory> category;
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="category_id")
	private List<ProductType> productType;
	
	public ProductCategory getParentCategory() {
		return parentCategory;
	}
	public void setParentCategory(ProductCategory parentCategory) {
		this.parentCategory = parentCategory;
	}
	public List<ProductCategory> getCategory() {
		return category;
	}
	public void setCategory(List<ProductCategory> category) {
		this.category = category;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Long getParentCategoryId() {
		return parentCategoryId;
	}
	public void setParentCategoryId(Long parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}
	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	public List<ProductType> getProductType() {
		return productType;
	}
	public void setProductType(List<ProductType> productType) {
		this.productType = productType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
