package com.mitosis.aviate.model.service;

import java.util.List;

import com.mitosis.aviate.model.ProductCategory;
import com.mitosis.aviate.model.ProductDetails;
import com.mitosis.aviate.model.ProductType;

public class ProductResponse extends ResponseModel {

	private List<ProductCategory> categories;
	private List<ProductType> productTypes;
	private List<ProductDetails> products;

	public List<ProductDetails> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDetails> products) {
		this.products = products;
	}

	public List<ProductCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<ProductCategory> categories) {
		this.categories = categories;
	}

	public List<ProductType> getProductTypes() {
		return productTypes;
	}

	public void setProductTypes(List<ProductType> productTypes) {
		this.productTypes = productTypes;
	}

}
