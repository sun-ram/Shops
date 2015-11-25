package com.mitosis.shopsbacker.responsevo;

import java.util.ArrayList;
import java.util.List;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.common.AddressVo;

public class AddressResponseVo extends ResponseModel {
	
	List<AddressVo> addressList = new ArrayList<AddressVo>();

	public List<AddressVo> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<AddressVo> addressList) {
		this.addressList = addressList;
	}

}
