package com.mitosis.shopsbacker.responsevo;

import java.util.ArrayList;
import java.util.List;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.order.SalesOrderReturnVo;

public class SalesOrderReturnResponseVo extends ResponseModel{
	
	public List<SalesOrderReturnVo> salesOrderReturnList = new ArrayList<SalesOrderReturnVo>();

	public List<SalesOrderReturnVo> getSalesOrderReturnList() {
		return salesOrderReturnList;
	}

	public void setSalesOrderReturnList(
			List<SalesOrderReturnVo> salesOrderReturnList) {
		this.salesOrderReturnList = salesOrderReturnList;
	}
	
	

}
