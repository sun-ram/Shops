package com.mitosis.shopsbacker.order.daoimpl;

import java.io.Serializable;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.model.OrderTax;
import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.order.dao.SalesOrderTaxDao;

/**
 * @author prabakaran
 *
 * @param <T>
 */
@Repository
public class SalesOrderTaxDaoImpl<T> extends CustomHibernateDaoSupport<T>
		implements SalesOrderTaxDao<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public void saveOrderTax(OrderTax orderTax) {
		try {
			save((T) orderTax);
		} catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateOrderTax(OrderTax orderTax) {
		try {
			update((T) orderTax);
		} catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deteteOrderTax(OrderTax orderTax) {
		try {
			delete((T) orderTax);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public OrderTax getOrderTax(String id) {
		return (OrderTax) getSession().get(OrderTax.class, id);
	}

	@Override
	public OrderTax getOrderTax(SalesOrder salesOrder) {
		DetachedCriteria criteria = DetachedCriteria.forClass(OrderTax.class);
		criteria.add(Restrictions.eq("salesOrder", salesOrder));
		return (OrderTax) findUnique(criteria);
	}

}
