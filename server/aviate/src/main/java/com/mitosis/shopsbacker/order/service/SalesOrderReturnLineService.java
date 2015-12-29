package com.mitosis.shopsbacker.order.service;

import com.mitosis.shopsbacker.model.SalesOrderReturnLine;

/**
 * @author Kartheeswaran
 *
 * @param <T>
 */
public interface SalesOrderReturnLineService<T> {
	
	public String createSalesOrderReturnLine(SalesOrderReturnLine orderReturnLine);
	
	public boolean updateSalesOrderReturnLine(SalesOrderReturnLine orderReturnLine);

	public boolean deleteSalesOrderReturnLine(SalesOrderReturnLine orderReturnLine);
	
	public SalesOrderReturnLine getSalesOrderReturnLineById(String id);
	
}
