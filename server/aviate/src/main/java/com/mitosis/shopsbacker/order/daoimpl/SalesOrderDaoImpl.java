package com.mitosis.shopsbacker.order.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.Merchant;
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

	@SuppressWarnings("unchecked")
	public List<SalesOrder> getSalesOrders(Store store, boolean isPaid) {
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(SalesOrder.class);
			criteria.add(Restrictions.eq("store", store));
			criteria.add(Restrictions.eq("ispaid", isPaid ? 'Y' : "N"));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			criteria.addOrder(Order.desc("created"));
			return (List<SalesOrder>) findAll(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<SalesOrder> getOrderList(Store store) {
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(SalesOrder.class);
			criteria.add(Restrictions.eq("store", store));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			criteria.addOrder(Order.desc("created"));

			return (List<SalesOrder>) findAll(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
	public List<SalesOrder> salesOrderDetailList(String fromDate,
			String toDate, Store store) {
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(SalesOrder.class);
			criteria.add(Restrictions.eq("store", store));
			criteria.add(Restrictions.ge("created",
					CommonUtil.stringToDate(fromDate)));
			criteria.add(Restrictions.le("deliveryDate",
					CommonUtil.stringToDate(toDate)));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return (List<SalesOrder>) findAll(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<SalesOrder> salesOrderDetailList(String fromDate,
			String toDate, Merchant merchant) {
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(SalesOrder.class);
			criteria.add(Restrictions.eq("merchant", merchant));
			criteria.add(Restrictions.ge("created",
					CommonUtil.stringToDate(fromDate)));
			criteria.add(Restrictions.le("deliveryDate",
					CommonUtil.stringToDate(toDate)));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return (List<SalesOrder>) findAll(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	public SalesOrder salesOrderById(String salesOrderId) {
		try {
			return (SalesOrder) getSession()
					.get(SalesOrder.class, salesOrderId);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@SuppressWarnings("unchecked")
	public void updateSalesOrder(SalesOrder salesOrder) {
		try {
			update((T) salesOrder);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}

	}

	@SuppressWarnings("unchecked")
	public void saveSalesOrder(SalesOrder salesOrder) {
		try {
			save((T) salesOrder);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<SalesOrder> getOrderList(Merchant merchant) {
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(SalesOrder.class);
			criteria.add(Restrictions.eq("merchant", merchant));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			criteria.addOrder(Order.desc("created"));

			return (List<SalesOrder>) findAll(criteria);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public SalesOrder getSalesOrder(String orderNo) {
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(SalesOrder.class);
			criteria.add(Restrictions.eq("orderNo", orderNo));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return (SalesOrder) findUnique(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	/**
	 * @author Prabakaran A
	 * @return List of SalesOrder
	 * @param Store store, Customer customer
	 */
	@SuppressWarnings("unchecked")
	public List<SalesOrder> getOrderList(Store store, Customer customer) {
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(SalesOrder.class);
			criteria.add(Restrictions.eq("store", store));
			criteria.add(Restrictions.eq("customer", customer));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return (List<SalesOrder>) findAll(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}
}