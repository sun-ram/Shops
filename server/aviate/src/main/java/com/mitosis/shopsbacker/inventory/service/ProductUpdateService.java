package com.mitosis.shopsbacker.inventory.service;

import java.util.List;

import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.model.ProductOffer;
import com.mitosis.shopsbacker.model.ProductType;
import com.mitosis.shopsbacker.model.Uom;

public interface ProductUpdateService<T> {
	
public void addCategory(ProductCategory productCategory);
	
	public ProductCategory selectCategoryById(ProductCategory selectCategory);
	
	public List<ProductCategory> getCategoryByParentId(String productCategoryId);
	
	public void updateCategory(ProductCategory selectCategory);
	
	public void updateProductType(ProductType productType);
	
	public List<ProductType> getProductType(String categoryIds);
	
	public void updateProductOffer(ProductOffer productOffer);
	
	public void updateUOM(Uom productUnitOfMeasure);
	
	public void addProductType(ProductType productType);
	
	public ProductType getSingleProductType(String productTypeId);
	
	public void removeSingleProductType(String productTypeId);

	public void removeCategory(ProductCategory productCategory);

}
