package com.mitosis.shopsbacker.inventory.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.inventory.dao.ProductOfferDao;
import com.mitosis.shopsbacker.model.Discount;
import com.mitosis.shopsbacker.model.ProductOffer;
/**
 * @author RiyazKhan.M
 */
@Repository("productOfferDaoImpl")
public class ProductOfferDaoImpl<T> extends CustomHibernateDaoSupport<T> implements
ProductOfferDao<T>, Serializable{

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public void addProductOffer(ProductOffer productOffer) {
		try {
			save((T) productOffer);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateProductOffer(ProductOffer productOffer) {
		try {
			update((T) productOffer);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteProductOffer(ProductOffer productOffer) {
		try {
			delete((T) productOffer);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductOffer> getAllProductOffer() {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ProductOffer.class);
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return ((List<ProductOffer>) findAll(criteria));
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
	}

}
