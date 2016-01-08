package com.mitosis.shopsbacker.customer.service;

import java.util.List;

import com.mitosis.shopsbacker.model.SalesOrder;

/**
 * @author prabakaran
 *
 * @param <T>
 */
public interface OrderHistoryService<T> {

	public List<SalesOrder> getOrderList(String customerId);
	
}
