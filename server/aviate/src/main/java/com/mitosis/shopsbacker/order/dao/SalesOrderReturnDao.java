package com.mitosis.shopsbacker.order.dao;

import java.util.List;

import com.mitosis.shopsbacker.model.SalesOrderReturn;
import com.mitosis.shopsbacker.model.Store;

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
	
	public List<SalesOrderReturn> getSalesOrderByStore(Store store);
}
