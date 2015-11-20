package com.mitosis.shopsbacker.responsevo;

import java.util.ArrayList;
import java.util.List;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.order.SalesOrderLineVo;

public class SalesOrderLineResponseVo extends ResponseModel{

	public List<SalesOrderLineVo> saleslistListVo = new ArrayList<SalesOrderLineVo>();

	public List<SalesOrderLineVo> getSaleslistListVo() {
		return saleslistListVo;
	}

	public void setSaleslistListVo(List<SalesOrderLineVo> saleslistListVo) {
		this.saleslistListVo = saleslistListVo;
	}
	

}
