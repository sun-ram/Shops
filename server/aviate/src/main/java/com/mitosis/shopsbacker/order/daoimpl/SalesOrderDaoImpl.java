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

@Repository
public class SalesOrderDaoImpl<T> extends CustomHibernateDaoSupport<T> implements
SalesOrderDao<T>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	CommonUtil util=new CommonUtil();

	@Override
	public List<SalesOrder> getSalesOrders(Store store) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(SalesOrder.class);
			criteria.add(Restrictions.eq("store", store));
			criteria.add(Restrictions.eq("ispaid", "Y"));
			return (List<SalesOrder>) findAll(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}
	
	@Override
	public List<SalesOrder> getOrderList(String salesOrderId, Store store) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(SalesOrder.class);
			criteria.add(Restrictions.eq("store", store));
			criteria.add(Restrictions.eq("salesOrderId", salesOrderId));
			return (List<SalesOrder>) findAll(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

	@Override
	public List<SalesOrder> getOrderList(Store store) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(SalesOrder.class);
			criteria.add(Restrictions.eq("store", store));
			return (List<SalesOrder>) findAll(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

	@Override
	public List<SalesOrder> getOrderList(Store store, OrderStatus status) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(SalesOrder.class);
			criteria.add(Restrictions.eq("store", store));
			criteria.add(Restrictions.eq("status", status));
			return (List<SalesOrder>) findAll(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}
	

	@Override
	public List<SalesOrder> salesOrderDetailList(String fromDate,String toDate,Store store) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(SalesOrder.class);
			criteria.add(Restrictions.eq("store", store));
			criteria.add(Restrictions.ge("created", util.stringToDate(fromDate)));
			criteria.add(Restrictions.le("created", util.stringToDate(toDate)));
			return (List<SalesOrder>) findAll(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

	@Override
	public SalesOrder salesOrderById(String salesOrderId) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(SalesOrder.class);
			criteria.add(Restrictions.eq("salesOrderId", salesOrderId));
			return ((List<SalesOrder>) findAll(criteria)).get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

	@Override
	public void updateSalesOrder(SalesOrder salesOrder) {
		try {
			update((T) salesOrder);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
	}




}