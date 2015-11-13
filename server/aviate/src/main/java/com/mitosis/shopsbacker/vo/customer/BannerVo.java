package com.mitosis.shopsbacker.vo.customer;

// Generated Nov 12, 2015 6:16:19 PM 

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.shopsbacker.vo.admin.MerchantVo;

/**
 * Banner Created by Sundaram C.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class BannerVo implements java.io.Serializable {

	private String bannerId;
	private String Updatedby;
	private String Createdby;
	private ImageVo image;
	private MerchantVo merchant;
	private StoreVo store;
	private String tabTitleSmall;
	private String tabTitleBold;
	private char isShopsbackerBanner;
	private char isactive;
	private Date created;
	private Date updated;

	

}
