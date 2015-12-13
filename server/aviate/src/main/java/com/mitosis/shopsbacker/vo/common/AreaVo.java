/**
 * 
 */
package com.mitosis.shopsbacker.vo.common;

import com.mitosis.shopsbacker.model.City;

/**
 * @author Anbukkani Gajendiran
 *
 */
public class AreaVo {

	private String areaId;
	private String name;
	private CityVo city;
	private String cityId;
	
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CityVo getCity() {
		return city;
	}
	public void setCity(CityVo city) {
		this.city = city;
	}
}
