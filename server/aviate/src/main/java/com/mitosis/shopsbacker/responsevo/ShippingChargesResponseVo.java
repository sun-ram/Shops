package com.mitosis.shopsbacker.responsevo;

import java.util.ArrayList;
import java.util.List;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.order.ShippingChargesVo;

public class ShippingChargesResponseVo extends ResponseModel{

	public List<ShippingChargesVo> shippingChargesList = new ArrayList<ShippingChargesVo>();

	public List<ShippingChargesVo> getShippingChargesList() {
		return shippingChargesList;
	}

	public void setShippingChargesList(List<ShippingChargesVo> shippingChargesList) {
		this.shippingChargesList = shippingChargesList;
	}

}
