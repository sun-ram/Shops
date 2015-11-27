package com.mitosis.shopsbacker.responsevo;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.customer.CustomerVo;

public class CustomerLoginResponseVo extends ResponseModel {

	public CustomerVo customer;

	public CustomerVo getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerVo customerVo) {
		this.customer = customerVo;
	}

}
