package com.mitosis.shopsbacker.inventory.serviceimpl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.inventory.dao.ProductImageDao;
import com.mitosis.shopsbacker.inventory.service.ProductImageService;
import com.mitosis.shopsbacker.model.ProductImage;
@Repository("productImageService")
public class ProductImageServiceImpl<T> implements ProductImageService<T>, Serializable {



	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	ProductImageDao productImageDao;

	@Override
	public ProductImage getProductImage(String productImageId) {
		return productImageDao.getProductImage(productImageId);
	}

	@Override
	public void deleteProductImage(ProductImage productImage) {
		productImageDao.deleteProductImage(productImage);
	}

}
