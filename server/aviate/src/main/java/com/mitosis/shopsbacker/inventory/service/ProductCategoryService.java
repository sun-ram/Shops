package com.mitosis.shopsbacker.inventory.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.model.Store;

/**
 * @author RiyazKhan.M
 */
public interface ProductCategoryService<T> {

	public List<ProductCategory> getCategoryListByStore(Store store);

	public ProductCategory getCategoryById(String categoryId);

	public List<ProductCategory> getProductCategoryList(Merchant merchant);

	public void getLeafCategories(List<ProductCategory> productCategoryList,
			List<ProductCategory> productCategories);

	public void setCategoryIds(List<ProductCategory> productCategoryList,
			List<String> ids);

	public void setParentCategories(List<ProductCategory> productCategoryList,
			List<ProductCategory> productCategories);
	
	public void addCategory(ProductCategory productCategory);
	
	public void updateCategory(ProductCategory productCategory);
	
	public void deleteCategory(ProductCategory productCategory);
	
	public List<ProductCategory> getRootProductCategoryList(Merchant merchant);

}