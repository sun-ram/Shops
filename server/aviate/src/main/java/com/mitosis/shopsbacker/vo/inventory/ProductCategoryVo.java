package com.mitosis.shopsbacker.vo.inventory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;

/**
 * @author fayaz
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ProductCategoryVo {
	
	private String productCategoryId;
	
	private String name;
	
	private MerchantVo merchant;
	
	private ProductCategoryVo category;
	
	private List<ProductCategoryVo> categories =new ArrayList<ProductCategoryVo>();
	
	private List<ProductTypeVo> productTypes =new ArrayList<ProductTypeVo>();
	
	private List<ProductVo> products=new ArrayList<ProductVo>();

	public List<ProductCategoryVo> getCategoriesVo() {
		return categories;
	}

	public void setCategoriesVo(List<ProductCategoryVo> categories) {
		this.categories = categories;
	}

	public String getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MerchantVo getMerchant() {
		return merchant;
	}

	public void setMerchant(MerchantVo merchant) {
		this.merchant = merchant;
	}

	public ProductCategoryVo getCategory() {
		return category;
	}

	public void setCategory(ProductCategoryVo category) {
		this.category = category;
	}

	public List<ProductTypeVo> getProductTypes() {
		return productTypes;
	}

	public void setProductTypes(List<ProductTypeVo> productTypes) {
		this.productTypes = productTypes;
	}

	public List<ProductVo> getProducts() {
		return products;
	}

	public void setProducts(List<ProductVo> products) {
		this.products = products;
	}
	
	

}
