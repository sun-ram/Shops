package com.mitosis.shopsbacker.order.dao;

import com.mitosis.shopsbacker.model.OrderTax;
import com.mitosis.shopsbacker.model.SalesOrder;

/**
 * @author prabakaran
 *
 * @param <T>
 */
public interface SalesOrderTaxDao<T> {

	public void saveOrderTax(OrderTax orderTax);

	public void updateOrderTax(OrderTax orderTax);

	public void deteteOrderTax(OrderTax orderTax);

	public OrderTax getOrderTax(String id);

	public OrderTax getOrderTax(SalesOrder salesOrder);

}
