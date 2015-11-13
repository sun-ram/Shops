package com.mitosis.shopsbacker.inventory.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.inventory.dao.ProductUpdateDao;
import com.mitosis.shopsbacker.inventory.service.ProductUpdateService;
import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.model.ProductOffer;
import com.mitosis.shopsbacker.model.ProductType;
import com.mitosis.shopsbacker.model.Uom;

@Service("productUpdateServiceImpl")
public class ProductUpdateServiceImpl<T> implements ProductUpdateService<T>, Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	ProductUpdateDao<T> productUpdateDao;
	
	
	public ProductUpdateDao<T> getProductUpdateDao() {
		return productUpdateDao;
	}

	public void setProductUpdateDao(ProductUpdateDao<T> productUpdateDao) {
		this.productUpdateDao = productUpdateDao;
	}

	@Override
	@Transactional
	public void addCategory(ProductCategory productCategory) {
		productUpdateDao.addCategory(productCategory);
		
	}

	@Override
	@Transactional
	public ProductCategory selectCategoryById(ProductCategory selectCategory) {
	     return productUpdateDao.selectCategoryById(selectCategory);
	}

	@Override
	@Transactional
	public List<ProductCategory> getCategoryByParentId(String productCategoryId) {
		return productUpdateDao.getCategoryByParentId(productCategoryId);
	}

	@Override
	@Transactional
	public void updateCategory(ProductCategory selectCategory) {
		productUpdateDao.updateCategory(selectCategory);
		
	}

	@Override
	@Transactional
	public void updateProductType(ProductType productType) {
		productUpdateDao.updateProductType(productType);
		
	}

	@Override
	@Transactional
	public List<ProductType> getProductType(String categoryIds) {
		return productUpdateDao.getProductType(categoryIds);
	}

	@Override
	@Transactional
	public void updateProductOffer(ProductOffer productOffer) {
		productUpdateDao.updateProductOffer(productOffer);
		
	}

	@Override
	@Transactional
	public void updateUOM(Uom productUnitOfMeasure) {
		productUpdateDao.updateUOM(productUnitOfMeasure);
		
	}

	@Override
	@Transactional
	public void addProductType(ProductType productType) {
		productUpdateDao.addProductType(productType);
		
	}

	@Override
	@Transactional
	public ProductType getSingleProductType(String productTypeId) {
		return productUpdateDao.getSingleProductType(productTypeId);
	}

	@Override
	@Transactional
	public void removeSingleProductType(String productTypeId) {
		productUpdateDao.removeSingleProductType(productTypeId);
		
	}

	@Override
	@Transactional
	public void removeCategory(ProductCategory productCategory) {
		productUpdateDao.removeCategory(productCategory);
		
	}

}
