package com.mitosis.shopsbacker.inventory.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.inventory.dao.ProductTypeDao;
import com.mitosis.shopsbacker.model.ProductType;

/**
 * @author fayaz
 */
@Repository
public class ProductTypeDaoImpl<T> extends CustomHibernateDaoSupport<T>
		implements ProductTypeDao<T>, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void addProductType(ProductType productType) {
		try {
			save((T) productType);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}

	}

	@Override
	public void updateProductType(ProductType productType) {
		try {
			update((T) productType);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}

	}

	@Override
	public void removeProductType(ProductType productType) {
		try {
			delete((T) productType);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}

	}

	@Override
	public ProductType getProductTypeById(String productTypeId) {
		try {
			return (ProductType) getSession().get(ProductType.class,
					productTypeId);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public List<ProductType> getAllProductType() {
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(ProductType.class);
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return (List<ProductType>) findAll(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

}
