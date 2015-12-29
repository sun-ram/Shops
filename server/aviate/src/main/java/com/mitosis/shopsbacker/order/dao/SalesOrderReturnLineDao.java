package com.mitosis.shopsbacker.order.dao;

import com.mitosis.shopsbacker.model.SalesOrderReturnLine;

/**
 * @author Kartheeswaran
 *
 * @param <T>
 */
public interface SalesOrderReturnLineDao<T> {
	
	public String createSalesOrderReturnLine(SalesOrderReturnLine orderReturnLine);
	
	public boolean updateSalesOrderReturnLine(SalesOrderReturnLine orderReturnLine);

	public boolean deleteSalesOrderReturnLine(SalesOrderReturnLine orderReturnLine);
	
	public SalesOrderReturnLine getSalesOrderReturnLineById(String id);
}
