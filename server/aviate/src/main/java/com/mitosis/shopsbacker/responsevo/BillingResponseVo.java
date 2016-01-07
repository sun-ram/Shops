package com.mitosis.shopsbacker.responsevo;

import java.util.ArrayList;
import java.util.List;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.order.BillingVo;

public class BillingResponseVo extends ResponseModel {
	
	public List<BillingVo> billsList = new ArrayList<BillingVo>();

	public List<BillingVo> getBillList() {
		return billsList;
	}

	public void setBillList(List<BillingVo> billsList) {
		this.billsList = billsList;
	}


}
