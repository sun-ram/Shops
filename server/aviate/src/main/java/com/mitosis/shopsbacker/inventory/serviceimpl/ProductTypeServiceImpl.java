package com.mitosis.shopsbacker.inventory.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.inventory.dao.ProductTypeDao;
import com.mitosis.shopsbacker.inventory.service.ProductTypeService;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.model.ProductType;
import com.mitosis.shopsbacker.vo.inventory.ProductCategoryVo;
import com.mitosis.shopsbacker.vo.inventory.ProductTypeVo;

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

	@Override
	@Transactional
	public boolean checkProductType(ProductCategory productCategory) {
		return productTypeDao.checkProductType(productCategory);
	}

	@Override
	@Transactional
	public List<ProductType> getAllProductTypeByMerchant(Merchant merchant) {
		return productTypeDao.getAllProductTypeByMerchant(merchant);
	}

	@Override
	@Transactional
	public List<ProductType> getProductTypeByCategory(
			ProductCategory productCategory) {
		return productTypeDao.getProductTypeByCategory(productCategory);
	}

	@Override
	public ProductTypeVo setProductTypeVo(ProductType productType) {
		ProductTypeVo productTypeVo = new ProductTypeVo();
		productTypeVo.setName(productType.getName());
		productTypeVo.setProductTypeId(productType.getProductTypeId());
		return productTypeVo;
	}
}
