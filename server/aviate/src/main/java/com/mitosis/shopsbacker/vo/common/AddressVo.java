package com.mitosis.shopsbacker.vo.common;

import javax.xml.bind.annotation.XmlRootElement;


import com.mitosis.shopsbacker.vo.customer.CustomerVo;

/**
 * @author prabakaran
 *
 * @param <T>
 * 
 *  Reviewed by Sundaram 27/11/2015
 */

@XmlRootElement
public class AddressVo {
	private String addressId;
	private StateVo state;
	private CountryVo country;
	private String landmark;
	private String address1;
	private String address2;
	private String city;
	private String pinCode;
	private String phoneNo;
	private String latitude;
	private String longitude;
	private CustomerVo customer;
	
	public CountryVo getCountry() {
		return country;
	}
	public void setCountry(CountryVo country) {
		this.country = country;
	}
	public String getAddressId() {
		return addressId;
	}
	public StateVo getState() {
		return state;
	}
	public String getLandmark() {
		return landmark;
	}
	public String getAddress1() {
		return address1;
	}
	public String getAddress2() {
		return address2;
	}
	public String getCity() {
		return city;
	}
	public String getPinCode() {
		return pinCode;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public String getLatitude() {
		return latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public void setState(StateVo state) {
		this.state = state;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public CustomerVo getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerVo customer) {
		this.customer = customer;
	}

}