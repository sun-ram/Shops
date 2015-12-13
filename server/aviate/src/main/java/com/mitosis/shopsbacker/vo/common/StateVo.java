package com.mitosis.shopsbacker.vo.common;

// Generated Nov 12, 2015 6:16:19 PM 

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * State Created by Sundaram C.
 */

@XmlRootElement
public class StateVo {

	private String stateId;
	private String name;
	private List<CityVo> city = new ArrayList<CityVo>();

	public List<CityVo> getCity() {
		return city;
	}
	public void setCity(List<CityVo> city) {
		this.city = city;
	}
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
