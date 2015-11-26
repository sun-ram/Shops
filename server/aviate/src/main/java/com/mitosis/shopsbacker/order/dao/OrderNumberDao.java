package com.mitosis.shopsbacker.order.dao;

import com.mitosis.shopsbacker.model.OrderNumber;
import com.mitosis.shopsbacker.model.Store;

/**
 * @author prabakaran
 *
 * @param <T>
 */
public interface OrderNumberDao<T> {

	public void saveOrderNumber(OrderNumber orderNumber);

	public void updateOrderNumber(OrderNumber orderNumber);

	public OrderNumber getOrderNumber(Store store);

	public void deleteOrderNumber(OrderNumber orderNumber);

	public OrderNumber getOrderNumberById(String orderNumberId);

}
