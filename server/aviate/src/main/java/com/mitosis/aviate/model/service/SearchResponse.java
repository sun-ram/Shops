package com.mitosis.aviate.model.service;

import java.util.List;

import org.codehaus.jettison.json.JSONObject;

import com.mitosis.aviate.model.ProductCategory;
import com.mitosis.aviate.model.ProductDetails;
import com.mitosis.aviate.model.ProductType;

public class SearchResponse extends ResponseModel {
	
//	private JSONObject category;

	private List<ProductCategory> category;
	

	public List<ProductCategory> getCategory() {
		return category;
	}

	public void setCategory(List<ProductCategory> category) {
		this.category = category;
	}

	private List<ProductDetails> products;
	
	private List<ProductType> productsTypes;



	public List<ProductDetails> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDetails> products) {
		this.products = products;
	}

	public List<ProductType> getProductsTypes() {
		return productsTypes;
	}

	public void setProductsTypes(List<ProductType> productsTypes) {
		this.productsTypes = productsTypes;
	}

}
