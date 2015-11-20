package com.mitosis.shopsbacker.inventory.dao;

import java.util.List;

import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.model.ProductType;

/**
 * @author fayaz
 */
public interface ProductTypeDao<T> {

	public void addProductType(ProductType productType);

	public void updateProductType(ProductType productType);

	public void removeProductType(ProductType productType);
	
	public boolean checkProductType(ProductCategory productCategory);

	public ProductType getProductTypeById(String productTypeId);

	public List<ProductType> getAllProductType();
	
	public List<ProductType> getAllProductTypeByMerchant(Merchant merchant);

}
