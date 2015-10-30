package com.mitosis.aviate.dao;


import java.util.List;

import org.codehaus.jettison.json.JSONObject;

import com.mitosis.aviate.model.MyCartModel;
import com.mitosis.aviate.model.ProductCategory;
import com.mitosis.aviate.model.ProductDetails;
import com.mitosis.aviate.model.ProductType;
import com.mitosis.aviate.model.ShippingChargeModel;
import com.mitosis.aviate.model.StoreModel;
import com.mitosis.aviate.model.TaxModel;

public interface ProductDao {
	
	public List<StoreModel> getShopList(JSONObject filterDetails) throws Exception;
	
	public List<TaxModel> getShopTax(JSONObject filterDetails) throws Exception;
	
	public List<ProductCategory> getCategoryList(Long storeId);
	
	public List<ProductDetails> getProductList(Long productTypeId);
	
	public ProductDetails getProductDetails(Long productId);
	
	public List<ProductDetails> getAllProductListByCategory(Long categoryId);
	
	public List<ProductCategory> getCategoryListById(Long categoryId);
	
	public void getCategoryIds(List<ProductCategory> productCategoryList, List<Long> ids);
	
	public void getLeafCategories(List<ProductCategory> productCategoryList , List<ProductCategory> productCategories);
	
	public void getParentCategories(List<ProductCategory> productCategoryList, List<ProductCategory> productCategories);
	
	public boolean deleteProductById(long productId);
	
	//my cart methods
	public void updateCart(MyCartModel mycart);

	public boolean removeFromCart(JSONObject reqObj) throws Exception;

	public List<MyCartModel> getMyCartList(int customerId, Long storeId);
	
	public List<ProductDetails> SearchProductResponse(Long storeId,String searchString);
	
	public List<ProductCategory> SearchProductCategoryResponse(Long storeId,String searchString);

	public List<ProductType> SearchProductTypeResponse(Long storeId,String searchString);
	
	public boolean deleteFromCart(JSONObject reqObj) throws Exception;

	public List<ShippingChargeModel> getShippingCharges(Long storeId);
	
	public List<ProductDetails> getMerchantProducts(Long merchantId);

	public List<ProductDetails> getFutureProducts(long parseLong);

	public List<ProductDetails> getTopCategories(Long merchantId);
	
	public List<String> getShopCityList();

	
	
}
