package com.mitosis.shopsbacker.inventory.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.inventory.dao.ProductCategoryDao;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.model.Store;

/**
 * @author RiyazKhan.M
 */
@Repository
public class ProductCategoryDaoImpl<T> extends CustomHibernateDaoSupport<T>
		implements ProductCategoryDao<T>, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public List<ProductCategory> getCategoryListByStore(Store store) {
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(ProductCategory.class);
			criteria.add(Restrictions.eq("store", store));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return (List<ProductCategory>) findAll(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public ProductCategory getCategoryById(String productCategoryId) {
		try {
			return (ProductCategory) getSession().get(ProductCategory.class,
					productCategoryId);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public List<ProductCategory> getProductCategoryList(Merchant merchant) {
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(ProductCategory.class);
			criteria.add(Restrictions.eq("merchant", merchant));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return (List<ProductCategory>) findAll(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public List<ProductCategory> getRootProductCategoryList(Merchant merchant) {
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(ProductCategory.class);
			criteria.add(Restrictions.eq("merchant", merchant));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			criteria.add(Restrictions.isNull("parentCategory"));
			return (List<ProductCategory>) findAll(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public void addCategory(ProductCategory productCategory) {
		try {
			save((T) productCategory);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}

	}

	@Override
	public void updateCategory(ProductCategory productCategory) {
		try {
			update((T) productCategory);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}

	}

	@Override
	public void deleteCategory(ProductCategory productCategory) {
		try {
			delete((T) productCategory);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}

	}

	@Override
	public List<ProductCategory> getallleafcategorylist(Merchant merchant) {
		try {
			Criteria crit = getSession().createCriteria(ProductCategory.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			Criteria prdCrit = crit.createCriteria("productTypes");
			prdCrit.add(Restrictions.eq("merchant", merchant));
			prdCrit.add(Restrictions.eq("isactive", 'Y'));
			List results = crit.list();
			return results;
		} catch (HibernateException e) {
			e.printStackTrace();
			throw (e);
		}

	}

}