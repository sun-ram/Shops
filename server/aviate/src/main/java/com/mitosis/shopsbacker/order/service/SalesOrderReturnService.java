package com.mitosis.shopsbacker.order.service;

import com.mitosis.shopsbacker.model.SalesOrderReturn;

public interface SalesOrderReturnService<T> {
	
public String createSalesOrderReturn(SalesOrderReturn orderReturn);
	
	public boolean updateSalesOrderReturn(SalesOrderReturn orderReturn);

	public boolean deleteSalesOrderReturn(SalesOrderReturn orderReturn);
	
	public SalesOrderReturn getSalesOrderReturnById(String id);
	
}
