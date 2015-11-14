package com.mitosis.shopsbacker.order.daoimpl;

import java.io.Serializable;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.OrderNumber;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.order.dao.OrderNumberDao;

/**
 * @author prabakaran
 *
 * @param <T>
 */
public class OrderNumberDaoImpl<T> extends CustomHibernateDaoSupport<T>
		implements OrderNumberDao<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public void saveOrderNumber(OrderNumber orderNumber) {
		try {
			save((T) orderNumber);
		} catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateOrderNumber(OrderNumber orderNumber) {
		try {
			update((T) orderNumber);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public OrderNumber getOrderNumber(Store store) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(OrderNumber.class);
		criteria.add(Restrictions.eq("store", store));
		return (OrderNumber) findUnique(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteOrderNumber(OrderNumber orderNumber) {
		try {
			delete((T) orderNumber);
		} catch (Exception e) {
			throw e;
		}
	}

}
