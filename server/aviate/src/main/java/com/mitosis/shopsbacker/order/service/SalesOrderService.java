package com.mitosis.shopsbacker.order.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Address;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.util.OrderStatus;
import com.mitosis.shopsbacker.vo.order.SalesOrderVo;
import com.mitosis.shopsbacker.vo.order.TransactionDetailVo;

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


	public List<SalesOrder> getOrderList(Merchant merchant);

	public List<SalesOrder> salesOrderDetailList(String fromDate,
			String toDate, Merchant merchant);

	public SalesOrderVo setSalesOrderVo (SalesOrder salesOrder, boolean excludeReturnQty) throws Exception ;

	public boolean paymentConfimation(String salesOrderNo, String paymentId,
			String paymentMethod, String requestId, String transactionNo,
			String responseCode, String responseMessage, String referenceNo);

	public SalesOrder getSalesOrder(String orderNo);

	public SalesOrderVo setSalesOrderVoEmp(SalesOrder salesOrder) throws Exception;

	public void productStockReduce(SalesOrder salesOrder) throws Exception;

	public List<SalesOrder> getSalesOrdersByBackerId(String shoperId);
	
	public TransactionDetailVo setTransactionDetails(SalesOrder salesOrder, 
			Address deliveryAddress, Address shippingAddress, Customer customer);
}