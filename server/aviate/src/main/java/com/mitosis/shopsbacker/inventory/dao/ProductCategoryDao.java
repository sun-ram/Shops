package com.mitosis.shopsbacker.inventory.dao;

import java.util.List;

import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.model.Store;

/**
 * @author RiyazKhan.M
 */
public interface ProductCategoryDao<T> {

	public List<ProductCategory> getCategoryListByStore(Store store);

	public ProductCategory getCategoryById(String productCategoryId);

	public List<ProductCategory> getProductCategoryList(Merchant merchant);
	
	public void addCategory(ProductCategory productCategory);
	
	public void updateCategory(ProductCategory productCategory);
	
	public void deleteCategory(ProductCategory productCategory);
	
	public List<ProductCategory> getRootProductCategoryList(Merchant merchant);
	
	public List<ProductCategory> getallleafcategorylist(Merchant merchant);
	
	public List<ProductCategory> getParentCategory(ProductCategory parentCategory);

}