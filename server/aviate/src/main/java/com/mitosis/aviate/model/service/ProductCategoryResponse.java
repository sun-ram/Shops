package com.mitosis.aviate.model.service;

import java.util.List;

import com.mitosis.aviate.model.ProductCategory;
import com.mitosis.aviate.model.ProductType;

public class ProductCategoryResponse extends ResponseModel {
	
	private List<ProductCategory> categories;

	private List<ProductType> productTypes;

	public List<ProductType> getProductTypes() {
		return productTypes;
	}

	public void setProductTypes(List<ProductType> productTypes) {
		this.productTypes = productTypes;
	}

	public List<ProductCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<ProductCategory> categories) {
		this.categories = categories;
	}
}
