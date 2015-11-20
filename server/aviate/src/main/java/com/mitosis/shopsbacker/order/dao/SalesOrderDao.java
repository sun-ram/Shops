package com.mitosis.shopsbacker.order.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.util.OrderStatus;

/**
 * @author fayaz
 */
public interface SalesOrderDao<T> {

	public List<SalesOrder> getSalesOrders(Store store, boolean isPaid);

	public List<SalesOrder> getOrderList(Store store);

	public List<SalesOrder> getOrderList(Store store, OrderStatus status);

	public List<SalesOrder> salesOrderDetailList(String fromDate,
			String toDate, Store store);

	public SalesOrder salesOrderById(String salesOrderId);

	public void updateSalesOrder(SalesOrder salesOrder);
	
	public void saveSalesOrder(SalesOrder salesOrder);
	
	public List<SalesOrder> getOrderList(String merchantId);
	
	public List<SalesOrder> salesOrderDetailList(String fromDate,
			String toDate, String merchantId);

}
