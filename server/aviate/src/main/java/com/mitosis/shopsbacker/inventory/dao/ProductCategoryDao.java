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

}
