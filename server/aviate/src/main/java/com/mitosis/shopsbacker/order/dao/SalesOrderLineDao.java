package com.mitosis.shopsbacker.order.dao;

import java.util.List;

import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.model.SalesOrderLine;

public interface SalesOrderLineDao<T> {
	
	public List<SalesOrderLine> getSalesOrderLineById(String id);

	public List<SalesOrderLine> getSalesOrderLineBysalesOrder(
			SalesOrder salesOrderId);
		
}
