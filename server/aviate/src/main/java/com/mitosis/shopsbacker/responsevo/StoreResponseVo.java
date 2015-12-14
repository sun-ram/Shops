package com.mitosis.shopsbacker.responsevo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.admin.StoreVo;
import com.mitosis.shopsbacker.vo.common.CityVo;

public class StoreResponseVo extends ResponseModel {

	public List<StoreVo> store = new ArrayList<StoreVo>();
	
	public  List<CityVo> cityList = new ArrayList<CityVo>();

	public List<StoreVo> getStore() {
		return store;
	}

	public void setStore(List<StoreVo> store) {
		this.store = store;
	}

	public List<CityVo> getCityList() {
		return cityList;
	}

	public void setCityList(List<CityVo> cityList) {
		this.cityList = cityList;
	}
	
	

}
