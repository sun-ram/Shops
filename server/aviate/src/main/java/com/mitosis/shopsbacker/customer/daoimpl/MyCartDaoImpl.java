package com.mitosis.shopsbacker.customer.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.customer.dao.MyCartDao;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.Favourite;
import com.mitosis.shopsbacker.model.MyCart;
import com.mitosis.shopsbacker.model.Store;

@Repository
public class MyCartDaoImpl<T> extends CustomHibernateDaoSupport<T> implements
MyCartDao<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public void updateCart(MyCart mycart) {
		try {
			update((T) mycart);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw(e);
		}

		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MyCart> getMyCartList(Customer customer, Store store) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Favourite.class);
		criteria.add(Restrictions.eq("customer", customer));
		criteria.add(Restrictions.eq("store", store));
		return (List<MyCart>) findAll(criteria);
	}

	@Override
	public void removeFromCart(MyCart mycart) {
		try {
			delete((T) mycart);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw(e);
		}
		
	}

}
