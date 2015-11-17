package com.mitosis.shopsbacker.vo.common;

// Generated Nov 12, 2015 6:16:19 PM 

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Country Created by Sundaram C.
 */
@XmlRootElement
public class CountryVo {
	private String countryId;
	private String name;
	private String code;
	private String currencyCode;
	private String currencyName;
	private List<StateVo> states = new ArrayList<StateVo>();
	public String getCountryId() {
		return countryId;
	}
	public String getName() {
		return name;
	}
	public String getCode() {
		return code;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public List<StateVo> getStates() {
		return states;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	public void setStates(List<StateVo> states) {
		this.states = states;
	}

}
