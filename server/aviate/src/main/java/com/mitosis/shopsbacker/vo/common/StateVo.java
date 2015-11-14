package com.mitosis.shopsbacker.vo.common;

// Generated Nov 12, 2015 6:16:19 PM 

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * State Created by Sundaram C.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class StateVo implements java.io.Serializable {

	private String stateId;
	private CountryVo country;
	private String name;
	private char isactive;
	private List<AddressVo> addresses = new ArrayList<AddressVo>();
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	public CountryVo getCountry() {
		return country;
	}
	public void setCountry(CountryVo country) {
		this.country = country;
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
	public List<AddressVo> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<AddressVo> addresses) {
		this.addresses = addresses;
	}

}
