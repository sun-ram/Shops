package com.mitosis.shopsbacker.responsevo;

import java.util.ArrayList;
import java.util.List;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.admin.StoreVo;

public class StoreResponseVo extends ResponseModel {

	public List<StoreVo> store = new ArrayList<StoreVo>();

	public List<StoreVo> getStore() {
		return store;
	}

	public void setStore(List<StoreVo> store) {
		this.store = store;
	}

}
