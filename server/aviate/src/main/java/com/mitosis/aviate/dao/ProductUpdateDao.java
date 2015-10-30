package com.mitosis.aviate.dao;

import java.util.List;

import org.codehaus.jettison.json.JSONObject;

import com.google.gson.JsonObject;
import com.mitosis.aviate.model.ProductCategory;
import com.mitosis.aviate.model.ProductDetails;
import com.mitosis.aviate.model.ProductImages;
import com.mitosis.aviate.model.ProductOffer;
import com.mitosis.aviate.model.ProductPriceDetails;
import com.mitosis.aviate.model.ProductType;
import com.mitosis.aviate.model.ProductUOM;
import com.mitosis.aviate.model.Units;

public interface ProductUpdateDao {
	
	public boolean addCategory(ProductCategory productCategory);
	
	public ProductCategory selectCategoryById(ProductCategory selectCategory);
	
	public List<ProductCategory> getCategoryByParentId(Long parentCategoryId);
	
	public void updateCategory(ProductCategory selectCategory);
	
//	public List<ProductCategory> getLeafCategory(Long storeId);
	
	public boolean updateProductType(ProductType productType);
	
	public void updateProduct(ProductDetails productDetails);
	
	public void updateProductPrice(ProductPriceDetails productPrice);
	
	public ProductImages getProductImageByImageName(String productName);
		
	public List<ProductType> getProductType(List<Long> categoryIds);
	
	public void updateProductOffer(ProductOffer productOffer);
	
	public void updateUOM(ProductUOM productUnitOfMeasure);
	
	public boolean updateUnits(Units units);
	
	public List<Units> getUnits();
	
	public boolean deleteUnit(Long unitsId);
	
	public void addProductType(ProductType productType);
	
	public ProductType getSingleProductType(long productTypeId);
	
	public boolean removeSingleProductType(long productTypeId);
	public boolean removeCategory(ProductCategory productCategory);
	public List<Units> getUnitsListByMerchant(JSONObject request);

}
