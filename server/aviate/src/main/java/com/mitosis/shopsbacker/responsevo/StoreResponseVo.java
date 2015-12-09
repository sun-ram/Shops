package com.mitosis.shopsbacker.responsevo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.admin.StoreVo;

public class StoreResponseVo extends ResponseModel {

	public List<StoreVo> store = new ArrayList<StoreVo>();
	
	public SortedSet<String> cityList = new TreeSet<String>();

	public List<StoreVo> getStore() {
		return store;
	}

	public void setStore(List<StoreVo> store) {
		this.store = store;
	}

	public SortedSet<String> getCityList() {
		return cityList;
	}

	public void setCityList(SortedSet<String> cityList) {
		this.cityList = cityList;
	}
	
	

}
