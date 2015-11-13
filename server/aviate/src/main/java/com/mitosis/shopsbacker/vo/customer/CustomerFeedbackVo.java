package com.mitosis.shopsbacker.vo.customer;

// Generated Nov 12, 2015 6:16:19 PM 

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.shopsbacker.vo.admin.MerchantVo;

/**
 * CustomerFeedback Created by Sundaram C.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CustomerFeedbackVo implements java.io.Serializable {

	private String customerFeedbackId;
	private MerchantVo merchant;
	private CustomerVo customerByCreatedby;
	private CustomerVo customerByCustomerId;
	private CustomerVo customerByUpdatedby;
	private StoreVo store;
	private String comments;
	private char isactive;
	private Date created;
	private Date updated;
	private List<SalesOrderVo> salesOrders = new ArrayList<SalesOrderVo>();



}
