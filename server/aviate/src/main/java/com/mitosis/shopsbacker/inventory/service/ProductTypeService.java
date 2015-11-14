package com.mitosis.shopsbacker.inventory.service;

import java.util.List;

import com.mitosis.shopsbacker.model.ProductType;

/**
 * @author fayaz
 */
public interface ProductTypeService<T> {

	public void addProductType(ProductType productType);

	public void updateProductType(ProductType productType);

	public void removeProductType(ProductType productType);

	public ProductType getProductTypeById(String productTypeId);

	public List<ProductType> getAllProductType();

}
