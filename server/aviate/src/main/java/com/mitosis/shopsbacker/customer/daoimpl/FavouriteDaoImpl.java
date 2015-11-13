package com.mitosis.shopsbacker.customer.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.customer.dao.FavouriteDao;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.Favourite;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.Store;

/**
 * @author prabakaran
 *
 * @param <T>
 */

@Repository
public class FavouriteDaoImpl<T> extends CustomHibernateDaoSupport<T> implements
		FavouriteDao<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public void updateFavourite(Favourite favourite) {
		try {
			update((T) favourite);
		} catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteFavourite(Favourite favourite) {
		try {
			delete((T) favourite);
		} catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Favourite> getFavourites(Customer customer, Store store) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Favourite.class);
		criteria.add(Restrictions.eq("customer", customer));
		criteria.add(Restrictions.eq("store", store));
		return (List<Favourite>) findAll(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Favourite> getFavourites(Customer customer, Product product,
			Store store) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Favourite.class);
		criteria.add(Restrictions.eq("customer", customer));
		criteria.add(Restrictions.eq("store", product));
		criteria.add(Restrictions.eq("store", store));
		return (List<Favourite>) findAll(criteria);
	}

}
