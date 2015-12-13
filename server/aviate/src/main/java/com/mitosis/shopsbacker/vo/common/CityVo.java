package com.mitosis.shopsbacker.vo.common;


public class CityVo {
	private String cityId;
	private String name;
	private StateVo statevo;
	private char isactive;
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getIsactive() {
		return isactive;
	}
	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}
	public StateVo getStatevo() {
		return statevo;
	}
	public void setStatevo(StateVo statevo) {
		this.statevo = statevo;
	}
	
}
