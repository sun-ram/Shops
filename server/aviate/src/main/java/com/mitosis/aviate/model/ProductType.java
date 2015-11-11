package com.mitosis.aviate.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.aviate.model.service.ResponseModel;

@XmlRootElement
@Entity
@Table(name = "product_type")
public class ProductType extends ResponseModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_type_id")
	private Long productTypeId;
	@Column(name="category_id")
	private Long categoryId;
	@Column(name="product_type_name")
	private String productTypeName;
	@Column(name="store_id")
	private String storeId;
	@Column(name="merchant_id")
	private String merchantId;
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="product_type_id")
	private List<ProductDetails> products;
	
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public Long getProductTypeId() {
		return productTypeId;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeId(Long productTypeId) {
		this.productTypeId = productTypeId;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public List<ProductDetails> getProducts() {
		return products;
	}
	public void setProducts(List<ProductDetails> products) {
		this.products = products;
	}
	
}
