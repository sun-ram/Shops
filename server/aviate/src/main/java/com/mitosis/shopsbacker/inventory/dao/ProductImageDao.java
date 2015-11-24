package com.mitosis.shopsbacker.inventory.dao;

import com.mitosis.shopsbacker.model.ProductImage;

public interface ProductImageDao<T> {

	public ProductImage getProductImage(String productImageId);

	public void deleteProductImage(ProductImage ProductImage);

}
