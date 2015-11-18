package com.mitosis.shopsbacker.inventory.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.inventory.dao.ProductDao;
import com.mitosis.shopsbacker.inventory.service.ProductService;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.model.ProductType;
import com.mitosis.shopsbacker.model.Uom;
/**
 * @author RiyazKhan.M
 */
@Service("productServiceImpl")
public class ProductServiceImpl<T> implements ProductService<T>, Serializable {

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

	@Override
	@Transactional
	public void addProduct(Product product) {
		productDao.addProduct(product);
		
	}

	@Override
	@Transactional
	public void updateProduct(Product product) {
		productDao.updateProduct(product);
		
	}

	@Override
	@Transactional
	public List<Product> getProductByUom(Uom uom){
		return productDao.getProductByUom(uom);
	}
}
