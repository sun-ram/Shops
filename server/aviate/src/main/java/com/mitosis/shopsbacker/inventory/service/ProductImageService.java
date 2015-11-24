package com.mitosis.shopsbacker.inventory.service;

import com.mitosis.shopsbacker.model.ProductImage;

public interface ProductImageService<T> {
	
	public ProductImage getProductImage(String productImageId);	
	
	
	public void deleteProductImage(ProductImage productImage);	

}
