package com.mitosis.shopsbacker.responsevo;

import java.util.ArrayList;
import java.util.List;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.admin.StoreVo;
import com.mitosis.shopsbacker.vo.order.SalesOrderVo;

public class SalesOrderResponseVo extends ResponseModel{
	
	public List<SalesOrderVo> salesOrderList = new ArrayList<SalesOrderVo>();
	
	public List<SalesOrderVo> getSalesOrderList() {
		return salesOrderList;
	}

	public void setSalesOrderList(List<SalesOrderVo> salesOrderList) {
		this.salesOrderList = salesOrderList;
	}

}
