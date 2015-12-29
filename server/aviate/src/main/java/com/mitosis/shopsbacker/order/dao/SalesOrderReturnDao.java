package com.mitosis.shopsbacker.order.dao;

import com.mitosis.shopsbacker.model.SalesOrderReturn;

/**
 * @author Kartheeswaran
 *
 * @param <T>
 */
public interface SalesOrderReturnDao<T> {
	
	public String createSalesOrderReturn(SalesOrderReturn orderReturn);
	
	public boolean updateSalesOrderReturn(SalesOrderReturn orderReturn);

	public boolean deleteSalesOrderReturn(SalesOrderReturn orderReturn);
	
	public SalesOrderReturn getSalesOrderReturnById(String id);
}
