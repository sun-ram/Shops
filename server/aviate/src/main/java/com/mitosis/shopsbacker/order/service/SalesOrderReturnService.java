package com.mitosis.shopsbacker.order.service;

import java.util.List;

import com.mitosis.shopsbacker.model.SalesOrderReturn;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.vo.order.SalesOrderReturnVo;

public interface SalesOrderReturnService<T> {
	
public String createSalesOrderReturn(SalesOrderReturn orderReturn);
	
	public boolean updateSalesOrderReturn(SalesOrderReturn orderReturn);

	public boolean deleteSalesOrderReturn(SalesOrderReturn orderReturn);
	
	public SalesOrderReturn getSalesOrderReturnById(String id);
	
	public List<SalesOrderReturn> getSalesOrderByStore(Store store);
	
	public SalesOrderReturnVo setSalesOrderToVo(SalesOrderReturn salesOrderReturn);
	
	public String getSalesOrderReturnRefundRequest(SalesOrderReturnVo salesOrderReturnVo) throws Exception;
	
}
