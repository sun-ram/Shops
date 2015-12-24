package com.mitosis.shopsbacker.order.dao;

import java.util.List;

import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.Merchant;
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
	
	public List<SalesOrder> getOrderList(Merchant merchant);
	
	public List<SalesOrder> getOrderList(Store store, Customer customer);
	
	public List<SalesOrder> salesOrderDetailList(String fromDate,
			String toDate, Merchant merchant);

	public SalesOrder getSalesOrder(String orderNo);

	public List<SalesOrder> getSalesOrdersByBackerId(String shoperId);

}
