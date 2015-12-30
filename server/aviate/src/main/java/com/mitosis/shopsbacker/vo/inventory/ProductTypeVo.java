package com.mitosis.shopsbacker.vo.inventory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ProductTypeVo {

	private String productTypeId;
	
	private String name;
	
	private ProductCategoryVo productCategory=new ProductCategoryVo();
	
	private MerchantVo merchant;
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductCategoryVo getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategoryVo productCategory) {
		this.productCategory = productCategory;
	}

	public MerchantVo getMerchant() {
		return merchant;
	}

	public void setMerchant(MerchantVo merchant) {
		this.merchant = merchant;
	}

	 
	
}
