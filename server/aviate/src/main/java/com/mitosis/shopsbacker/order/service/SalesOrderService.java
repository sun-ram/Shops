package com.mitosis.shopsbacker.order.service;

import java.util.List;

import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.util.OrderStatus;
import com.mitosis.shopsbacker.vo.order.SalesOrderVo;

/**
 * @author fayaz
 */
public interface SalesOrderService<T> {

	public List<SalesOrder> getSalesOrders(Store store);

	public List<SalesOrder> getOrderList(Store store);

	public List<SalesOrder> getOrderList(Store store, OrderStatus status);

	public List<SalesOrder> salesOrderDetailList(String fromDate,
			String toDate, Store store);

	public SalesOrder getSalesOrderById(String salesOrderId);

	public void updateSalesOrder(SalesOrder salesOrder);
	
	public void saveSalesOrder(SalesOrder salesOrder);

	public void conformPayment(String salesOrderId, String transactionNo,
			String paymentMethod);
	
	public List<SalesOrder> getOrderList(String merchantId);
	
	public List<SalesOrder> salesOrderDetailList(String fromDate,
			String toDate, String merchantId);
	
	public SalesOrderVo setSalesOrderVo (SalesOrder salesOrder) throws Exception ;


}
