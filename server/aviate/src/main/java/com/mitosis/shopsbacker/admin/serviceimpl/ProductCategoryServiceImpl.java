package com.mitosis.shopsbacker.admin.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.dao.ProductCategoryDao;
import com.mitosis.shopsbacker.admin.service.ProductCategoryService;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.model.Store;

@Service("productCategoryServiceImpl")
public class ProductCategoryServiceImpl<T> implements ProductCategoryService<T>, Serializable {

	@Autowired
	ProductCategoryDao productCategoryDao;
	
	@Override
	@Transactional
	public List<ProductCategory> getCategoryListByStore(Store store) {
		return productCategoryDao.getCategoryListByStore(store);
	}

	@Override
	@Transactional
	public List<ProductCategory> getCategoryListById(String categoryId) {
		return productCategoryDao.getCategoryListById(categoryId);
	}
	@Override
	@Transactional	
	public void getLeafCategories(List<ProductCategory> productCategoryList, List<ProductCategory> productCategories){
		for(ProductCategory productCategory:productCategoryList){
			if(productCategory.getProductCategories().size()>0){
				getLeafCategories(productCategory.getProductCategories(), productCategories);
			}
			else{
				ProductCategory category = new ProductCategory();
				category.setProductCategoryId(productCategory.getProductCategoryId());
				category.setName(productCategory.getName());
				category.setStore(productCategory.getStore());
				productCategories.add(category);
			}
		}
	}
	
	@Override
	@Transactional
	public void getCategoryIds(List<ProductCategory> productCategoryList,List<String> ids){
		for(ProductCategory productCategory:productCategoryList){
			ids.add(productCategory.getProductCategoryId());
			if(productCategory.getProductCategories().size()>0){
				getCategoryIds(productCategory.getProductCategories(),ids);
			}
		}
	}
	
	@Override
	@Transactional
	public void getParentCategories(List<ProductCategory> productCategoryList, List<ProductCategory> productCategories){
		for(ProductCategory productCategory:productCategoryList){
			ProductCategory category = new ProductCategory();
			category.setProductCategoryId(productCategory.getProductCategoryId());
			category.setName(productCategory.getName());
			productCategories.add(category);
			if(productCategory.getProductCategories().size()>0){
				getParentCategories(productCategory.getProductCategories(), productCategories);
			}
		}
	}

	@Override
	@Transactional
	public List<ProductCategory> getProductCategoryList(Merchant merchant) {
		return productCategoryDao.getProductCategoryList(merchant);
	}

}
