package com.mitosis.shopsbacker.order.service;

import com.mitosis.shopsbacker.model.OrderNumber;
import com.mitosis.shopsbacker.model.Store;

/**
 * @author prabakaran
 *
 * @param <T>
 */
public interface OrderNumberService<T> {

	public void saveOrderNumber(OrderNumber orderNumber);

	public void updateOrderNumber(OrderNumber orderNumber);

	public OrderNumber getOrderNumber(Store store);
	
	public String getSalesOrderNumber(Store store);

	public void deleteOrderNumber(OrderNumber orderNumber);

}
