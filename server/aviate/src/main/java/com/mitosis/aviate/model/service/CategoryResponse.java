package com.mitosis.aviate.model.service;

import java.util.List;

import com.mitosis.aviate.model.ProductCategory;

public class CategoryResponse extends ResponseModel{

	private List<ProductCategory> category;

	public List<ProductCategory> getCategory() {
		return category;
	}

	public void setCategory(List<ProductCategory> category) {
		this.category = category;
	}

}
