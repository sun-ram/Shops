package com.mitosis.shopsbacker.admin.dao;

import java.util.List;

import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.model.Store;


public interface ProductCategoryDao<T> {
	
	
	public List<ProductCategory> getCategoryListByStore(Store store);
	
	public List<ProductCategory> getCategoryListById(String productCategoryId);
	
	public List<ProductCategory> getProductCategoryList(Merchant merchant);

	
}
