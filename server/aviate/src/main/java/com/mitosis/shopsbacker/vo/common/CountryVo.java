package com.mitosis.shopsbacker.vo.common;

// Generated Nov 12, 2015 6:16:19 PM 

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Country Created by Sundaram C.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CountryVo implements java.io.Serializable {

	private String countryId;
	private String name;
	private String code;
	private String currencyCode;
	private String currencyName;
	private char isactive;
	private List<AddressVo> addresses = new ArrayList<AddressVo>();
	private List<StateVo> states = new ArrayList<StateVo>();

}
