package com.mitosis.shopsbacker.customer.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitosis.shopsbacker.admin.dao.StoreDao;
import com.mitosis.shopsbacker.customer.dao.CustomerDao;
import com.mitosis.shopsbacker.customer.service.OrderHistoryService;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.order.dao.SalesOrderDao;

@Service("orderHistoryServiceImpl")
public class OrderHistoryServiceImpl<T> implements OrderHistoryService<T> {
	
	@Autowired
	SalesOrderDao<T> salesOrderDao;

	@Autowired
	CustomerDao<T> customerDao;
	
	@Autowired
	StoreDao<T> storeDao;
	
	/**
	 * @author Prabakaran A
	 * @return List of SalesOrder
	 * @param String storeId, String customerId
	 */
	public List<SalesOrder> getOrderList(String customerId) {
		Customer customer = customerDao.getCustomerInfoById(customerId);
		return salesOrderDao.getOrderList(null, customer);
	}

}
