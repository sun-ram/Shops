package com.mitosis.shopsbacker.vo.admin;

// Generated Nov 12, 2015 6:16:19 PM 

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * MerchantRequest Created by Sundaram C.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class MerchantRequestVo implements java.io.Serializable {

	private String merchantRequestId;
	private AddressVo address;
	private String name;
	private String email;
	private char isactive;
	private Date created;
	private String createdby;
	private Date updated;
	private String updatedby;

	
}
