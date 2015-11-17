package com.mitosis.shopsbacker.responsevo;

import java.util.List;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.common.CountryVo;

public class CountryResponse extends ResponseModel {

	List<CountryVo> countries;

	public List<CountryVo> getCountries() {
		return countries;
	}

	public void setCountries(List<CountryVo> countries) {
		this.countries = countries;
	}

}
