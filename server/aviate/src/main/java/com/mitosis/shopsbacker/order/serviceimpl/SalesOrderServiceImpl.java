package com.mitosis.shopsbacker.order.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.order.dao.SalesOrderDao;
import com.mitosis.shopsbacker.order.service.SalesOrderService;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.OrderStatus;

/**
 * @author fayaz
 */
@Service("salesOrderServiceImpl")
public class SalesOrderServiceImpl<T> implements SalesOrderService<T>,
		Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	SalesOrderDao<T> salesOrderDao;

	public SalesOrderDao<T> getSalesOrderDao() {
		return salesOrderDao;
	}

	public void setSalesOrderDao(SalesOrderDao<T> salesOrderDao) {
		this.salesOrderDao = salesOrderDao;
	}

	@Override
	@Transactional
	public List<SalesOrder> getSalesOrders(Store store) {
		return salesOrderDao.getSalesOrders(store, true);
	}

	@Override
	@Transactional
	public List<SalesOrder> getOrderList(Store store) {
		return salesOrderDao.getOrderList(store);
	}

	@Override
	@Transactional
	public List<SalesOrder> getOrderList(Store store, OrderStatus status) {
		return salesOrderDao.getOrderList(store, status);
	}

	@Override
	@Transactional
	public List<SalesOrder> salesOrderDetailList(String fromDate,
			String toDate, Store store) {
		return salesOrderDao.salesOrderDetailList(fromDate, toDate, store);
	}

	@Override
	@Transactional
	public SalesOrder salesOrderById(String salesOrderId) {
		return salesOrderDao.salesOrderById(salesOrderId);
	}

	@Override
	@Transactional
	public void updateSalesOrder(SalesOrder salesOrder) {
		salesOrderDao.updateSalesOrder(salesOrder);

	}

	@Override
	@Transactional
	public void conformPayment(String salesOrderId, String transactionNo,
			String paymentMethod) {
		SalesOrder salesOrder = salesOrderDao.salesOrderById(salesOrderId);
		if (salesOrder != null) {
			salesOrder.setIspaid('Y');
			salesOrder.setStatus(OrderStatus.Initialized.toString());
			salesOrder.setPaymentMethod(paymentMethod);
			salesOrder.setTransactionNo(transactionNo);
			salesOrderDao.updateSalesOrder(salesOrder);
		}

	}

}
