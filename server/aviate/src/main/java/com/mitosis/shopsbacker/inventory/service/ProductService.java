package com.mitosis.shopsbacker.inventory.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.model.ProductType;
import com.mitosis.shopsbacker.model.Uom;
/**
 * @author RiyazKhan.M
 */
public interface ProductService<T> {
	
	public List<Product> getProductListByType(ProductType productType);
	
	public Product getProduct(String productId);
	
	public List<Product> getProductListByCategoty(ProductCategory productCategory);
	
	public void deleteProduct(Product product);
	
	public void addProduct(Product product);
	
	public void updateProduct(Product product);
	
	public List<Product> getProductByUom(Uom uom);

}
