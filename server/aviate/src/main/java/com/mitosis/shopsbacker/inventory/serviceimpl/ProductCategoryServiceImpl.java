package com.mitosis.shopsbacker.inventory.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.inventory.dao.ProductCategoryDao;
import com.mitosis.shopsbacker.inventory.service.ProductCategoryService;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.vo.inventory.ProductCategoryVo;

/**
 * @author RiyazKhan.M
 */
@Service("productCategoryServiceImpl")
public class ProductCategoryServiceImpl<T> implements
		ProductCategoryService<T>, Serializable {

	@Autowired
	ProductCategoryDao productCategoryDao;

	 
	public ProductCategoryDao getProductCategoryDao() {
		return productCategoryDao;
	}

	public void setProductCategoryDao(ProductCategoryDao productCategoryDao) {
		this.productCategoryDao = productCategoryDao;
	}

	@Override
	public List<ProductCategory> getCategoryListByStore(Store store) {
		return productCategoryDao.getCategoryListByStore(store);
	}

	@Override
	public ProductCategory getCategoryById(String categoryId) {
		return productCategoryDao.getCategoryById(categoryId);
	}

	@Override
	public void getLeafCategories(List<ProductCategory> productCategoryList,
			List<ProductCategory> productCategories) {
		for (ProductCategory productCategory : productCategoryList) {
			if (productCategory.getProductCategories().size() > 0) {
				getLeafCategories(productCategory.getProductCategories(),
						productCategories);
			} else {
				ProductCategory category = new ProductCategory();
				category.setProductCategoryId(productCategory
						.getProductCategoryId());
				category.setName(productCategory.getName());
				category.setStore(productCategory.getStore());
				productCategories.add(category);
			}
		}
	}

	@Override
	public void setCategoryIds(List<ProductCategory> productCategoryList,
			List<String> ids) {
		for (ProductCategory productCategory : productCategoryList) {
			ids.add(productCategory.getProductCategoryId());
			if (productCategory.getProductCategories().size() > 0) {
				setCategoryIds(productCategory.getProductCategories(), ids);
			}
		}
	}

	@Override
	public void setParentCategories(List<ProductCategory> productCategoryList,
			List<ProductCategory> productCategories) {
		for (ProductCategory productCategory : productCategoryList) {
			ProductCategory category = new ProductCategory();
			category.setProductCategoryId(productCategory
					.getProductCategoryId());
			category.setName(productCategory.getName());
			productCategories.add(category);
			if (productCategory.getProductCategories().size() > 0) {
				setParentCategories(productCategory.getProductCategories(),
						productCategories);
			}
		}
	}

	@Override
	public List<ProductCategory> getProductCategoryList(Merchant merchant) {
		return productCategoryDao.getProductCategoryList(merchant);
	}

	@Override
	public void addCategory(ProductCategory productCategory) {
		productCategoryDao.addCategory(productCategory);
		
	}

	@Override
	public void updateCategory(ProductCategory productCategory) {
		productCategoryDao.updateCategory(productCategory);
		
	}

	@Override
	public void deleteCategory(ProductCategory productCategory) {
		productCategoryDao.deleteCategory(productCategory);
		
	}

	@Override
	public List<ProductCategory> getRootProductCategoryList(Merchant merchant){
		 return productCategoryDao.getRootProductCategoryList(merchant);
	}

	@Override
	public List<ProductCategory> getallleafcategorylist(Merchant merchant) {
		return productCategoryDao.getallleafcategorylist(merchant);
	}

	@Override
	public ProductCategoryVo setProductCategoryVo(ProductCategory productCategory) {
		ProductCategoryVo category = new ProductCategoryVo();
		category.setProductCategoryId(productCategory
				.getProductCategoryId());
		category.setName(productCategory.getName());
		return category;
	}

	@Override
	public List<ProductCategory> getParentCategory(
			ProductCategory parentCategory) {
		return productCategoryDao.getParentCategory(parentCategory);
	}
}