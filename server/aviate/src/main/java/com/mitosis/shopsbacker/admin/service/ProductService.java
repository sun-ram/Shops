package com.mitosis.shopsbacker.admin.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.model.ProductType;

public interface ProductService<T> {
	
	public List<Product> getProductListByType(ProductType productType);
	
	public Product getProduct(String productId);
	
	public List<Product> getProductListByCategoty(ProductCategory productCategory);
	
	public void deleteProduct(Product product);

}
