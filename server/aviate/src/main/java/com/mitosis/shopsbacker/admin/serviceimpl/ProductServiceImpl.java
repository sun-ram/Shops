package com.mitosis.shopsbacker.admin.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.dao.ProductDao;
import com.mitosis.shopsbacker.admin.service.ProductService;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.model.ProductType;

@Service("productServiceImpl")
public class ProductServiceImpl<T> implements ProductService<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	ProductDao productDao;

	@Override
	@Transactional
	public List<Product> getProductListByType(ProductType productType) {
		return productDao.getProductListByType(productType);
	}

	@Override
	@Transactional
	public Product getProduct(String productId) {
		return productDao.getProduct(productId);
	}

	@Override
	@Transactional
	public List<Product> getProductListByCategoty(
			ProductCategory productCategory) {
		return productDao.getProductListByCategoty(productCategory);
	}

	@Override
	@Transactional
	public void deleteProduct(Product product) {
		productDao.deleteProduct(product);
		
	}

}
