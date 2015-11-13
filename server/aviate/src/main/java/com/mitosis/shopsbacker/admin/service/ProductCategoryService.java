package com.mitosis.shopsbacker.admin.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.model.Store;

public interface ProductCategoryService<T> {
	
	public List<ProductCategory> getCategoryListByStore(Store store);
	
	public List<ProductCategory> getCategoryListById(String categoryId);
	
	public List<ProductCategory> getProductCategoryList(Merchant merchant);
	
	public void getLeafCategories(List<ProductCategory> productCategoryList , List<ProductCategory> productCategories);

	public void getCategoryIds(List<ProductCategory> productCategoryList, List<String> ids);
	
	public void getParentCategories(List<ProductCategory> productCategoryList, List<ProductCategory> productCategories);

	

}
