package com.mitosis.shopsbacker.responsevo;

import java.util.ArrayList;
import java.util.List;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.order.OrderNumberVo;

public class OrderNumberResponseVo extends ResponseModel {

	public List<OrderNumberVo> orderNumberList = new ArrayList<OrderNumberVo>();

	public OrderNumberVo orderNumber; 
	
	public List<OrderNumberVo> getOrderNumberList() {
		return orderNumberList;
	}

	public void setOrderNumberList(List<OrderNumberVo> orderNumberList) {
		this.orderNumberList = orderNumberList;
	}
	
	public OrderNumberVo getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(OrderNumberVo orderNumber) {
		this.orderNumber = orderNumber;
	}
}
