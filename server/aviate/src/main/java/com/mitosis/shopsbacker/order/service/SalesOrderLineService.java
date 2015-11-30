package com.mitosis.shopsbacker.order.service;

import java.util.List;

import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.model.SalesOrderLine;
import com.mitosis.shopsbacker.vo.order.SalesOrderLineVo;
import com.mitosis.shopsbacker.vo.order.SalesOrderVo;

public interface SalesOrderLineService<T> {
	
	public List<SalesOrderLine> getSalesOrderLineById(String id);
	
	public List<SalesOrderLine> getSalesOrderLineBysalesOrder(SalesOrder salesOrder);
	
	public List<SalesOrderLineVo> setSalesOrderLineVo (List<SalesOrderLine> list)throws Exception ;
	
}
