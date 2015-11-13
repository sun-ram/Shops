package com.mitosis.shopsbacker.vo.customer;

// Generated Nov 12, 2015 6:16:19 PM 

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Image Created by Sundaram C.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ImageVo implements java.io.Serializable {

	private String imageId;
	private String name;
	private String type;
	private String url;
	private char isactive;
	private String createdby;
	private String updatedby;
	private Date created;
	private Date updated;
	private List<ProductImageVo> productImages = new ArrayList<ProductImageVo>();
	private List<BannerVo> banners = new ArrayList<BannerVo>();
	private List<UserVo> users = new ArrayList<UserVo>();
	private List<ProductVo> products = new ArrayList<ProductVo>();

}
