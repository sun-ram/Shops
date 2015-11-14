package com.mitosis.shopsbacker.inventory.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.inventory.dao.ProductTypeDao;
import com.mitosis.shopsbacker.inventory.service.ProductTypeService;
import com.mitosis.shopsbacker.model.ProductType;

/**
 * @author fayaz
 */
@Service("productTypeServiceImpl")
public class ProductTypeServiceImpl<T> implements ProductTypeService<T>,
		Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	ProductTypeDao<T> productTypeDao;

	public ProductTypeDao<T> getProductTypeDao() {
		return productTypeDao;
	}

	public void setProductTypeDao(ProductTypeDao<T> productTypeDao) {
		this.productTypeDao = productTypeDao;
	}

	@Override
	@Transactional
	public void addProductType(ProductType productType) {
		productTypeDao.addProductType(productType);

	}

	@Override
	@Transactional
	public void updateProductType(ProductType productType) {
		productTypeDao.updateProductType(productType);

	}

	@Override
	@Transactional
	public void removeProductType(ProductType productType) {
		productTypeDao.removeProductType(productType);

	}

	@Override
	@Transactional
	public ProductType getProductTypeById(String productTypeId) {
		return productTypeDao.getProductTypeById(productTypeId);
	}

	@Override
	@Transactional
	public List<ProductType> getAllProductType() {
		return productTypeDao.getAllProductType();
	}
}
