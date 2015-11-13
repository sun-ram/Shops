package com.mitosis.shopsbacker.inventory.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.inventory.dao.ProductUpdateDao;
import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.model.ProductOffer;
import com.mitosis.shopsbacker.model.ProductType;
import com.mitosis.shopsbacker.model.Uom;

@Repository
public class ProductUpdateDaoImpl<T> extends CustomHibernateDaoSupport<T> implements
ProductUpdateDao<T>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	public void addCategory(ProductCategory productCategory) {
		try {
			save((T) productCategory);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw(e);
		}
		
	}


	@Override
	public ProductCategory selectCategoryById(ProductCategory selectCategory) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ProductCategory.class);
			criteria.add(Restrictions.eq("productCategoryId", selectCategory));
			return ((List<ProductCategory>) findAll(criteria)).get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

	
	@Override
	public List<ProductCategory> getCategoryByParentId(String productCategoryId) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ProductCategory.class);
			criteria.add(Restrictions.eq("productCategoryId", productCategoryId));
			return (List<ProductCategory>) findAll(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

	
	@Override
	public void updateCategory(ProductCategory selectCategory) {
		try {
			update((T) selectCategory);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
	}

	
	@Override
	public void updateProductType(ProductType productType) {
		try {
			update((T) productType);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
	}

	
	@Override
	public List<ProductType> getProductType(String productTypeId) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ProductType.class);
			criteria.add(Restrictions.eq("productTypeId", productTypeId));
			return (List<ProductType>) findAll(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

	
	@Override
	public void updateProductOffer(ProductOffer productOffer) {
		try {
			update((T) productOffer);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
	}

	
	@Override
	public void updateUOM(Uom productUnitOfMeasure) {
		try {
			update((T) productUnitOfMeasure);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
	}

	
	@Override
	public void addProductType(ProductType productType) {
		try {
			save((T) productType);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
	}

	
	@Override
	public ProductType getSingleProductType(String productTypeId) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ProductCategory.class);
			criteria.add(Restrictions.eq("productTypeId", productTypeId));
			return ((List<ProductType>) findAll(criteria)).get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

	
	@Override
	public void removeSingleProductType(String productTypeId) {
		try {
			delete((T) productTypeId);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
	}

	
	@Override
	public void removeCategory(ProductCategory productCategory) {
		try {
			delete((T) productCategory);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

}
