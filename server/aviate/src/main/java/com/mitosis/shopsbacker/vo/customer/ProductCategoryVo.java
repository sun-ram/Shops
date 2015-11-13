package com.mitosis.shopsbacker.vo.customer;

// Generated Nov 12, 2015 6:16:19 PM 

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.shopsbacker.vo.admin.MerchantVo;

/**
 * ProductCategory Created by Sundaram C.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ProductCategoryVo implements java.io.Serializable {

	private String productCategoryId;
	private UserVo userByUpdatedby;
	private UserVo userByCreatedby;
	private MerchantVo merchant;
	private ProductCategoryVo productCategory;
	private StoreVo store;
	private String name;
	private Integer orderSequence;
	private char isactive;
	private Date created;
	private Date updated;
	private List<ProductVo> products = new ArrayList<ProductVo>();
	private List<ProductCategoryVo> productCategories = new ArrayList<ProductCategoryVo>();
	private List<ProductTypeVo> productTypes = new ArrayList<ProductTypeVo>();
	public String getProductCategoryId() {
		return productCategoryId;
	}
	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	public UserVo getUserByUpdatedby() {
		return userByUpdatedby;
	}
	public void setUserByUpdatedby(UserVo userByUpdatedby) {
		this.userByUpdatedby = userByUpdatedby;
	}
	public UserVo getUserByCreatedby() {
		return userByCreatedby;
	}
	public void setUserByCreatedby(UserVo userByCreatedby) {
		this.userByCreatedby = userByCreatedby;
	}
	public MerchantVo getMerchant() {
		return merchant;
	}
	public void setMerchant(MerchantVo merchant) {
		this.merchant = merchant;
	}
	public ProductCategoryVo getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(ProductCategoryVo productCategory) {
		this.productCategory = productCategory;
	}
	public StoreVo getStore() {
		return store;
	}
	public void setStore(StoreVo store) {
		this.store = store;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getOrderSequence() {
		return orderSequence;
	}
	public void setOrderSequence(Integer orderSequence) {
		this.orderSequence = orderSequence;
	}
	public char getIsactive() {
		return isactive;
	}
	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public List<ProductVo> getProducts() {
		return products;
	}
	public void setProducts(List<ProductVo> products) {
		this.products = products;
	}
	public List<ProductCategoryVo> getProductCategories() {
		return productCategories;
	}
	public void setProductCategories(List<ProductCategoryVo> productCategories) {
		this.productCategories = productCategories;
	}
	public List<ProductTypeVo> getProductTypes() {
		return productTypes;
	}
	public void setProductTypes(List<ProductTypeVo> productTypes) {
		this.productTypes = productTypes;
	}

	

}
