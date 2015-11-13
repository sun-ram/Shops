package com.mitosis.shopsbacker.admin.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mitosis.shopsbacker.admin.dao.ProductCategoryDao;
import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.model.Store;

@Repository
public class ProductCategoryDaoImpl<T> extends CustomHibernateDaoSupport<T> implements
ProductCategoryDao<T>, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public List<ProductCategory> getCategoryListByStore(Store store) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ProductCategory.class);
			criteria.add(Restrictions.eq("store", store));
			return (List<ProductCategory>) findAll(criteria);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw(e);
		}
	}

	@Override
	public List<ProductCategory> getCategoryListById(String productCategoryId) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ProductCategory.class);
			criteria.add(Restrictions.eq("productCategoryId", productCategoryId));
			return (List<ProductCategory>) findAll(criteria);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw(e);
		}
	}

	@Override
	public List<ProductCategory> getProductCategoryList(Merchant merchant) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ProductCategory.class);
			criteria.add(Restrictions.eq("merchant", merchant));
			return (List<ProductCategory>) findAll(criteria);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw(e);
		}
	}

}
