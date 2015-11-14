package com.mitosis.shopsbacker.order.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.order.dao.SalesOrderDao;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.OrderStatus;

/**
 * @author fayaz
 */
@Repository
public class SalesOrderDaoImpl<T> extends CustomHibernateDaoSupport<T>
		implements SalesOrderDao<T>, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public List<SalesOrder> getSalesOrders(Store store, boolean isPaid) {
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(SalesOrder.class);
			criteria.add(Restrictions.eq("store", store));
			criteria.add(Restrictions.eq("ispaid", isPaid ? 'Y' : "N"));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return (List<SalesOrder>) findAll(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public List<SalesOrder> getOrderList(Store store) {
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(SalesOrder.class);
			criteria.add(Restrictions.eq("store", store));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return (List<SalesOrder>) findAll(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public List<SalesOrder> getOrderList(Store store, OrderStatus status) {
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(SalesOrder.class);
			criteria.add(Restrictions.eq("store", store));
			criteria.add(Restrictions.eq("status", status.toString()));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return (List<SalesOrder>) findAll(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public List<SalesOrder> salesOrderDetailList(String fromDate,
			String toDate, Store store) {
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(SalesOrder.class);
			criteria.add(Restrictions.eq("store", store));
			criteria.add(Restrictions.ge("created",
					CommonUtil.stringToDate(fromDate)));
			criteria.add(Restrictions.le("created",
					CommonUtil.stringToDate(toDate)));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return (List<SalesOrder>) findAll(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public SalesOrder salesOrderById(String salesOrderId) {
		try {
			return (SalesOrder) getSession()
					.get(SalesOrder.class, salesOrderId);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public void updateSalesOrder(SalesOrder salesOrder) {
		try {
			update((T) salesOrder);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}

	}
}