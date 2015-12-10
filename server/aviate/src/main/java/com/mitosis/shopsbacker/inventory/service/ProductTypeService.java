package com.mitosis.shopsbacker.inventory.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.model.ProductType;
import com.mitosis.shopsbacker.vo.inventory.ProductCategoryVo;
import com.mitosis.shopsbacker.vo.inventory.ProductTypeVo;

/**
 * @author fayaz
 */
public interface ProductTypeService<T> {

	public void addProductType(ProductType productType);

	public void updateProductType(ProductType productType);

	public void removeProductType(ProductType productType);

	public ProductType getProductTypeById(String productTypeId);
	
	public boolean checkProductTypeWithname(ProductCategory productCategory,String name);

	public List<ProductType> getAllProductType();

	public boolean checkProductType(ProductCategory productCategory);

	public List<ProductType> getAllProductTypeByMerchant(Merchant merchant);

	public List<ProductType> getProductTypeByCategory(
			ProductCategory productCategory);
	
	public ProductTypeVo setProductTypeVo(ProductType productType);
	
	public List<ProductTypeVo> prepareProductTypeVoList(Merchant merchant); 

}
